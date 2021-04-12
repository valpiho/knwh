import React, {Component} from 'react';

class AddWarehouse extends Component {
    constructor() {
        super()
        this.state = {
            "title": "",
            "description": "",
            "storageQuantity": ""
        }
    }

    onChange = (event) => {
        this.setState({[event.target.name]: event.target.value})
    }

    onSubmit = (event) => {
        event.preventDefault();
        const newWarehouse = {
            "title": this.state.title,
            "description": this.state.description,
            "storageQuantity": this.state.storageQuantity
        }
        console.log(newWarehouse)
    }

    render() {
        return (
            <React.Fragment>
                <div
                    className="align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 className="h2">Add Warehouse Form</h1>
                </div>
                <form onSubmit={this.onSubmit}>
                    <div className="row">
                        <div className="col-md-3 mb-3">
                            <label>Title</label>
                            <input type="text"
                                   className="form-control"
                                   name="title"
                                   value={this.state.title}
                                   onChange={this.onChange}
                            />
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-md-6 mb-3">
                            <label>Description</label>
                            <textarea rows="3"
                                      className="form-control"
                                      name="description"
                                      value={this.state.description}
                                      onChange={this.onChange}
                            />
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-md-2 mb-2">
                            <label>Number of storage cells</label>
                            <input type="text"
                                   className="form-control"
                                   name="storageQuantity"
                                   value={this.state.storageQuantity}
                                   onChange={this.onChange}
                            />
                        </div>
                    </div>

                    <hr className="mb-4" />
                        <button className="btn btn-primary btn-lg" type="submit">Create warehouse</button>
                </form>
            </React.Fragment>
        );
    }
}

export default AddWarehouse;
