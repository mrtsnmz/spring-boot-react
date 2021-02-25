import React, { useState, useEffect } from "react";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";
import { InputTextarea } from "primereact/inputtextarea";
import { Button } from "primereact/button";
import { FileUpload } from "primereact/fileupload";
import { InputMask } from "primereact/inputmask";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

import { fetchFile, uploadFile, deleteFile } from "../actions";

import ilIlce from "../asserts/il-ilce.json";
import "../DialogDemo.css";

const Modal = ({
  formAttributes,
  setFormAttributes,
  setFile,
  formSubmit,
  file
}) => {
  const [cities, setCities] = useState([]);
  const [districts, setDistricts] = useState([]);

  useEffect(() => {
    let data = JSON.parse(JSON.stringify(ilIlce));
    let cities = [];
    for (const city of data.data) {
      cities.push({ name: city.il_adi });
    }
    setCities(cities);
  }, []);

  useEffect(() => {
    if (formAttributes.payload.city) {
      let districts = [];
      let data = JSON.parse(JSON.stringify(ilIlce));
      let index = data.data.findIndex(
        (city) => city.il_adi === formAttributes.payload.city
      );
      for (const district of data.data[index].ilceler) {
        districts.push({ name: district.ilce_adi });
      }
      setDistricts(districts);
    }
  }, [formAttributes.payload.city]);

  const fetchFiles = async (id) => {
    fetchFile(id)
      .then((res) => {
        if (res.data.length > 0) {
          setFile(res.data);
        }
      })
      .catch(err => console.error(err));
  };

  const uploadHandler = ({ files }) => {
    const [file] = files;
    const fileReader = new FileReader();
    fileReader.onload = (e) => {
      uploadFile(formAttributes.payload.id, e.target.result)
        .then((res) => fetchFiles(formAttributes.payload.id))
        .catch((err) => console.error(err));
    };
    fileReader.readAsDataURL(file);
  };

  return (
    <Dialog
      header={"Information"}
      style={{ width: "50vw" }}
      visible={formAttributes.visible}
      onHide={() => {
        setFile([]);
        setFormAttributes({
          visible: false,
          payload: {},
          type: "",
        });
      }}
    >
      <form onSubmit={() => formSubmit()}>
        <div className="p-fluid p-formgrid p-grid">
          <div className="p-field p-col-12 p-md-6">
            <label htmlFor="firstname6">Name</label>
            <InputText
              id="firstname6"
              type="text"
              required={true}
              value={formAttributes.payload.name}
              onChange={(e) =>
                setFormAttributes((prevState) => ({
                  ...prevState,
                  payload: {
                    ...prevState.payload,
                    name: e.target.value,
                  },
                }))
              }
            />
          </div>
          <div className="p-field p-col-12 p-md-6">
            <label htmlFor="lastname6">Surname</label>
            <InputText
              id="lastname6"
              type="text"
              required={true}
              value={formAttributes.payload.surname}
              onChange={(e) =>
                setFormAttributes((prevState) => ({
                  ...prevState,
                  payload: {
                    ...prevState.payload,
                    surname: e.target.value,
                  },
                }))
              }
            />
          </div>
          <div className="p-field p-col-12">
            <label htmlFor="address">Description</label>
            <InputTextarea
              id="address"
              type="text"
              rows="4"
              value={formAttributes.payload.description}
              onChange={(e) =>
                setFormAttributes((prevState) => ({
                  ...prevState,
                  payload: {
                    ...prevState.payload,
                    description: e.target.value,
                  },
                }))
              }
            />
          </div>
          <div className="p-field p-col-12 p-md-6">
            <label htmlFor="city">City</label>
            <Dropdown
              inputId="city"
              options={cities}
              required={true}
              appendTo={document.body}
              value={{ name: formAttributes.payload.city }}
              onChange={(e) => {
                setFormAttributes((prevState) => ({
                  ...prevState,
                  payload: {
                    ...prevState.payload,
                    city: e.target.value.name,
                    district: null,
                  },
                }));
              }}
              placeholder="Select"
              optionLabel="name"
            />
          </div>
          <div className="p-field p-col-12 p-md-3">
            <label htmlFor="state">District</label>
            <Dropdown
              inputId="district"
              value={{
                name: formAttributes.payload.district,
              }}
              options={districts}
              required={true}
              appendTo={document.body}
              onChange={(e) => {
                setFormAttributes((prevState) => ({
                  ...prevState,
                  payload: {
                    ...prevState.payload,
                    district: e.target.value.name,
                  },
                }));
              }}
              placeholder="Select"
              optionLabel="name"
            />
          </div>
          <div className="p-field p-col-12 p-md-3">
            <label htmlFor="zip">Phone</label>
            <InputMask
              id="phone"
              required={true}
              mask="(999) 999-9999"
              value={formAttributes.payload.phone}
              onChange={(e) =>
                setFormAttributes((prevState) => ({
                  ...prevState,
                  payload: {
                    ...prevState.payload,
                    phone: e.target.value,
                  },
                }))
              }
            />
          </div>
          {formAttributes.type === "update" ? (
            <>
              <div className="p-mb-2">
                <FileUpload
                  name="File"
                  accept=".pdf"
                  customUpload={true}
                  uploadHandler={uploadHandler}
                  mode="basic"
                  auto={true}
                  chooseLabel="Upload file"
                />
              </div>
              {file.length > 0 ? (
                <div className="card">
                  <DataTable value={file} className="p-datatable-sm">
                    <Column field="id" header="ID"></Column>
                    <Column
                      field="id"
                      header="Action"
                      body={(data) => (
                        <Button
                          icon="pi pi-times"
                          type="button"
                          className="p-button-danger p-mb-1"
                          onClick={async () => {
                            deleteFile(data.id)
                              .then((res) => {
                                if (file.length === 1) {
                                  setFile([]);
                                } else {
                                  fetchFiles(data.studentId);
                                }
                              })
                              .catch((err) => console.err(err));
                          }}
                        ></Button>
                      )}
                    ></Column>
                  </DataTable>
                </div>
              ) : null}
            </>
          ) : null}
          <Button label="SUBMIT" icon="pi pi-check" type="submit" autoFocus />
        </div>
      </form>
    </Dialog>
  );
};

export default Modal;
