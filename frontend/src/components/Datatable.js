import React, { useState, useRef, useContext } from "react";
import { StudentContext } from "../StudentContext";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { confirmPopup } from "primereact/confirmpopup";
import { Toast } from "primereact/toast";
import Modal from "./Modal";

import {
  addStudent,
  deleteStudentById,
  updateStudent,
  fetchFile,
} from "../actions";

const Datatable = () => {
  const [students, setStudents] = useContext(StudentContext);
  const [formAttributes, setFormAttributes] = useState({
    status: false,
    payload: {},
    type: "",
  });

  const [file, setFile] = useState([]);

  const toast = useRef(null);

  const accept = (id) => {
    deleteStudentById(id)
      .then((res) => setStudents(res.data))
      .catch((err) => console.error(err));

    toast.current.show({
      severity: "info",
      summary: "Confirmed",
      detail: "Student deleted",
      life: 3000,
    });
  };

  const reject = () => {};

  const confirm2 = (event, id) => {
    confirmPopup({
      target: event.currentTarget,
      message: "Do you want to delete this record?",
      icon: "pi pi-info-circle",
      acceptClassName: "p-button-danger",
      accept: () => accept(id),
      reject,
    });
  };

  function formSubmit() {
    if (formAttributes.type === "update") {
      updateStudent(formAttributes.payload)
        .then((res) => {
          let index = students.findIndex(
            (student) => student.id === formAttributes.payload.id
          );
          setStudents((prevState) => [
            ...prevState,
            (prevState[index] = formAttributes.payload),
          ]);
        })
        .catch();
    } else if (formAttributes.type === "register") {
      addStudent(formAttributes.payload)
        .then((res) =>
          setStudents((prevState) => [
            ...prevState,
            { ...formAttributes.payload, id: res },
          ])
        )
        .catch();
    }
  }

  const fetchFiles = async (id) => {
    fetchFile(id)
      .then((res) => {
        if (res.data.length > 0) {
          setFile(res.data);
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <div>
      <Toast ref={toast} />

      <Button
        label="Add New Student"
        icon="pi pi-plus"
        className="p-button-success p-mr-2 p-mb-2"
        onClick={() =>
          setFormAttributes({ visible: true, payload: {}, type: "register" })
        }
      />

      <div className="p-grid p-fluid dashboard">
        <div className="p-col-24 p-lg-12">
          <div className="card">
            <h1 style={{ fontSize: "16px" }}>Students</h1>
            <DataTable
              value={students}
              className="p-datatable-customers"
              rows={5}
              style={{ marginBottom: "20px" }}
              paginator
            >
              <Column field="name" header="Name" sortable></Column>
              <Column field="surname" header="Surname" sortable></Column>
              <Column field="phone" header="Phone" sortable></Column>
              <Column field="city" header="City" sortable></Column>
              <Column field="district" header="District" sortable></Column>
              <Column
                field="description"
                header="Description"
                sortable
              ></Column>
              <Column
                header="View"
                body={(student) => (
                  <>
                    <Button
                      icon="pi pi-search"
                      type="button"
                      className="p-button-success p-mr-2 p-mb-1"
                      onClick={async () => {
                        await fetchFiles(student.id);
                        setFormAttributes({
                          visible: true,
                          payload: student,
                          type: "update",
                        });
                      }}
                    ></Button>
                    <Button
                      icon="pi pi-times"
                      type="button"
                      className="p-button-danger p-mb-1"
                      onClick={(e) => confirm2(e, student.id)}
                    ></Button>
                    {formAttributes.visible ? (
                      <Modal
                        formAttributes={formAttributes}
                        setFormAttributes={setFormAttributes}
                        file={file}
                        setFile={setFile}
                        formSubmit={formSubmit}
                        students={students}
                      />
                    ) : null}
                  </>
                )}
              ></Column>
            </DataTable>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Datatable;
