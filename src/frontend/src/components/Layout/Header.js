import React, {Component} from 'react';
import {Link} from "react-router-dom";

class Header extends Component {
    render() {
        return (
                <header className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-white border-bottom shadow-sm">

                    <h5 className="my-0 mr-md-auto font-weight-normal">Company name</h5>

                    <nav className="my-2 my-md-0 mr-md-3">
                        <Link to="#" className="p-2 text-dark">Enterprise</Link>
                        <Link to="#" className="p-2 text-dark">Support</Link>
                        <Link to="#" className="p-2 text-dark">Tasks</Link>
                    </nav>

                    <div>
                        <button type="button" className="btn btn-outline-primary mr-2">Login</button>
                        <button type="button" className="btn btn-primary">Sign-up</button>
                    </div>

                </header>
        );
    }
}

export default Header;
