import React, {useState, useEffect} from "react";
import axios from '../axios/axios'
import {Redirect} from "react-router-dom";

const AddCategory = () =>{

    const [categoryNameField, setCategoryName] = useState("");
    const [redirect, setRedirect] = useState(false);

    const addNewCategory = (e) =>
    {
        e.preventDefault();
        axios.post("http://localhost:8080/api/category", {name: categoryNameField})
            .then(response => {
                setRedirect(true);
            }).catch(error => {
                console.log(error);
            })
    };
    const goBack = e =>
    {
        e.preventDefault();
        setRedirect(true);
    };
    return(

        <div className="container">
            { redirect  && <Redirect to={"/category"}  /> }
            <div className="row">
                <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div className="card card-signin my-5">
                        <div className="card-body">
                            <h5 className="card-title text-center">Додади нова категорија</h5>
                            <form className="form-signin">
                                <div className="form-label-group">
                                    <input className="form-control" type="text"  placeholder="Внеси име"  autoFocus value={categoryNameField} onChange={(event) => setCategoryName(event.target.value)}/>
                                        <label htmlFor="name">Име</label>
                                </div>
                                <button className="btn btn-primary btn-sm m-2" type="submit" onClick={addNewCategory}>Зачувај</button>
                                <button type="button" className="btn btn-secondary btn-sm" onClick={goBack}>Откажи</button>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
export default AddCategory;