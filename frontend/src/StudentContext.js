import React, { useState, createContext, useEffect } from "react";
import axios from "axios";

export const StudentContext = createContext();

export const StudentProvider = (props) => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/student")
      .then((response) => {
        setStudents(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <StudentContext.Provider value={[students, setStudents]}>
      {props.children}
    </StudentContext.Provider>
  );
};
