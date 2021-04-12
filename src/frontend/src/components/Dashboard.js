import React, {Component} from 'react';
import Warehouse from "./Company/Warehouse";
import * as Icon from 'react-feather';
import CreateProjectButton from "./Company/CreateProjectButton";

class Dashboard extends Component {
    render() {
        return (
            <>
                <div
                    className="align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 className="h2">Dashboard</h1>
                </div>
                <div className="row mb-5">
                    <div className="col-xl-3 col-md-6">
                        <div className="card card-stats">
                            <div className="card-body">
                                <div className="row">
                                    <div className="col">
                                        <h5 className="card-title text-uppercase text-muted mb-0">Total Orders</h5>
                                        <span className="h5 mb-0">12 In process</span> <br/>
                                        <span className="h5 mb-0">897 Finished</span>
                                    </div>
                                    <div className="col-auto">
                                        <div className="icon icon-shape">
                                            <Icon.List />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-3 col-md-6">
                        <div className="card card-stats">
                            <div className="card-body">
                                <div className="row">
                                    <div className="col">
                                        <h5 className="card-title text-uppercase text-muted mb-0">Total in Shipping</h5>
                                        <span className="h5 mb-0">34 In process</span> <br/>
                                        <span className="h5 mb-0">589 Finished</span>
                                    </div>
                                    <div className="col-auto">
                                        <div className="icon icon-shape">
                                            <Icon.Package />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-3 col-md-6">
                        <div className="card card-stats">
                            <div className="card-body">
                                <div className="row">
                                    <div className="col">
                                        <h5 className="card-title text-uppercase text-muted mb-0">Tasks</h5>
                                        <span className="h5 mb-0">12 in Orders</span> <br/>
                                        <span className="h5 mb-0">5 in Shipping</span>
                                    </div>
                                    <div className="col-auto">
                                        <div className="icon icon-shape">
                                            <Icon.Grid />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <Warehouse />
                <CreateProjectButton />
            </>
        );
    }
}

export default Dashboard;
