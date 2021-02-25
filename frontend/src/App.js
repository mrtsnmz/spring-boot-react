import React from "react";

import { StudentProvider } from "./StudentContext";

import DataTable from "./components/Datatable";
import Header from "./components/Header";
import SideBar from "./components/SideBar";

import "primereact/resources/themes/saga-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";

import "./layout/layout.scss";
import "./App.css";

function App() {
  return (
    <StudentProvider>
      <div className={"layout-wrapper layout-static"}>
        <Header />
        <SideBar />

        <div className="layout-main">
          <DataTable />
        </div>
      </div>
    </StudentProvider>
  );
}

export default App;
