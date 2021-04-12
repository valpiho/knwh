import React from 'react';
import { Link } from 'react-router-dom'

const CreateProjectButton = () => {
    return (
        <Link to="/addProject" className="btn btn-primary btn-lg btn-block mt-5">
            Add warehouse
        </Link>
    )
}

export default CreateProjectButton;
