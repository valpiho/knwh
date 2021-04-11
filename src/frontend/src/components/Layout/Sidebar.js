import React, {Component} from 'react';

class Sidebar extends Component {
    render() {
        return (
            <div className="sidebar-sticky pt-3">
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <a className="nav-link active" href="#">Dashboard <span className="sr-only">(current)</span></a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Orders</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Shipments</a>
                    </li>
                </ul>
                <hr/>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <a className="nav-link" href="#">Tasks</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Products</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Warehouses</a>
                    </li>
                </ul>
                <hr/>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <a className="nav-link" href="#">Customers</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Vendors</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Personnel</a>
                    </li>
                </ul>
            </div>
        );
    }
}

export default Sidebar;
