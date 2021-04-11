import './App.css';
import React from "react";
import Dashboard from "./components/Dashboard";
import Sidebar from "./components/Layout/Sidebar";
import Header from "./components/Layout/Header";

function App() {

    return (
        <div>
            <Header />
            <div className="container-fluid">
                <div className="row">
                    <nav id="sidebarMenu" className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                        <Sidebar />
                    </nav>
                    <div className="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                        <Dashboard />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default App;
