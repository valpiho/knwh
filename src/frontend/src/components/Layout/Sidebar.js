import React, {Component} from 'react';
import {Link} from "react-router-dom";

class Sidebar extends Component {
    render() {
        return (
            <div className="sidebar-sticky pt-3">
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="/" className="nav-link active">Dashboard <span className="sr-only">(current)</span></Link>
                    </li>
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Orders</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Shipments</Link>
                    </li>
                </ul>
                <hr/>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Tasks</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Products</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Warehouses</Link>
                    </li>
                </ul>
                <hr/>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Customers</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Vendors</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="#" className="nav-link">Personnel</Link>
                    </li>
                </ul>
            </div>
        );
    }
}

export default Sidebar;
