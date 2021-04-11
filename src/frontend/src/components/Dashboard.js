import React, {Component} from 'react';
import Warehouse from "./Company/Warehouse";

class Dashboard extends Component {
    render() {
        return (
            <>
                <div
                    className="align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 className="h2">Dashboard</h1>
                </div>
                <Warehouse />
                <button type="button" className="btn btn-primary btn-lg btn-block mt-5">Add new warehouse</button>
            </>
        );
    }
}

export default Dashboard;
