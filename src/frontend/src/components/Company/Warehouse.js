import React, {Component} from 'react';
import * as Icon from 'react-feather';

class Warehouse extends Component {
    render() {
        return (
            <div className="card">
                <div className="card-header">
                    <div className="row justify-content-between mx-2">
                        <h5>Warehouse title</h5>
                        <div>
                            <Icon.Edit /> <Icon.XSquare />
                        </div>
                    </div>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="col-xl-3 col-md-6">
                            <div className="card card-stats">
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col">
                                            <h5 className="card-title text-uppercase text-muted mb-0">Total items</h5>
                                            <span className="h4 font-weight-bold mb-0">350,897</span>
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
                                            <h5 className="card-title text-uppercase text-muted mb-0">Total storage spaces</h5>
                                            <span className="h4 font-weight-bold mb-0">2,356 of 4,230</span>
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
                                            <h5 className="card-title text-uppercase text-muted mb-0">Categories</h5>
                                            <span className="h4 font-weight-bold mb-0">924</span>
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
                        <div className="col-xl-3 col-md-6">
                            <div className="card card-stats">
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col">
                                            <h5 className="card-title text-uppercase text-muted mb-0">Performance</h5>
                                            <span className="h4 font-weight-bold mb-0">49,65%</span>
                                        </div>
                                        <div className="col-auto">
                                            <div className="icon icon-shape">
                                                <Icon.Repeat />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <p className="card-text">With supporting text below as a natural lead-in to additional
                        content.</p>
                    <a href="#" className="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        );
    }
}

export default Warehouse;
