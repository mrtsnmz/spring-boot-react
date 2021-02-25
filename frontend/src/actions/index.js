import axios from "axios";

export const fetchStudents = async () => {
  return new Promise(async (resolve, reject) => {
    try {
      const res = await axios.get("http://localhost:8080/api/v1/student");
      return resolve(res);
    } catch (ex) {
      return reject(ex);
    }
  });
};

export const addStudent = async (payload) => {
  return new Promise(async (resolve, reject) => {
    try {
      const res = await axios.post(
        "http://localhost:8080/api/v1/student",
        payload
      );
      return resolve(res);
    } catch (ex) {
      return reject(ex);
    }
  });
};

export const deleteStudentById = async (id) => {
  return new Promise(async (resolve, reject) => {
    try {
      const res = await axios.delete(
        `http://localhost:8080/api/v1/student/${id}`
      );
      return resolve(res);
    } catch (ex) {
      return reject(ex);
    }
  });
};

export const updateStudent = async (payload) => {
  return new Promise(async (resolve, reject) => {
    try {
      await axios.put(
        `http://localhost:8080/api/v1/student/${payload.id}`,
        payload
      );
      return resolve();
    } catch (ex) {
      return reject(ex);
    }
  });
};

export const uploadFile = async (id, file) => {
  return new Promise(async (resolve, reject) => {
    try {
      await axios.post(
        `http://localhost:8080/api/v1/student/upload/${id}`,
        file
      );
      return resolve();
    } catch (ex) {
      return reject(ex);
    }
  });
};

export const fetchFile = async (id) => {
  return new Promise(async (resolve, reject) => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/v1/student/file/${id}`
      );
      return resolve(res);
    } catch (ex) {
      return reject(ex);
    }
  });
};

export const deleteFile = async (id) => {
  return new Promise(async (resolve, reject) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/v1/student/file-delete/${id}`
      );
      return resolve();
    } catch (ex) {
      return reject(ex);
    }
  });
};
