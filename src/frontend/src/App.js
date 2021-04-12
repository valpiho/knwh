import './App.css';
import React from "react";
import Dashboard from "./components/Dashboard";
import Sidebar from "./components/Layout/Sidebar";
import Header from "./components/Layout/Header";

import { BrowserRouter as Router, Route} from "react-router-dom";
import AddWarehouse from "./components/Company/AddWarehouse";

function App() {

    return (
        <Router>
            <Header />
            <div className="container-fluid">
                <div className="row mr-1">
                    <nav id="sidebarMenu" className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                        <Sidebar />
                    </nav>
                    <div className="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                        <Route exact path="/" component={Dashboard}/>
                        <Route exact path="/addProject" component={AddWarehouse}/>
                    </div>
                </div>
            </div>
        </Router>
    )
}

export default App;
