import React, {useState, useEffect} from "react";
import axios from '../axios/axios'
import {Redirect} from "react-router-dom";
import { useParams } from "react-router-dom";

const AddTest = () =>{

    const [testNameField, setTestName] = useState("");
    const [redirectId, setRedirectId] = useState(-1);

    let { id } = useParams();

    const addNewTest = (e) =>
    {
        e.preventDefault();

        axios.post("http://localhost:8080/api/test/" +id, {name: testNameField})
            .then(response => {
                setRedirectId(id);
                console.log("yes");
            })
            .catch(error => {
                console.log(error);
            })
    };

    const goBack = e =>
    {
      e.preventDefault();
      setRedirectId(id);
    };

    return(
        <div className="container">

            { redirectId > -1 && <Redirect to={"/category/test/" + redirectId}  /> }

            <div className="row">
                <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div className="card card-signin my-5">
                        <div className="card-body">
                            <h5 className="card-title text-center">Додади нов тест</h5>
                            <form className="form-signin">
                                <div className="form-label-group">
                                    <input className="form-control" type="text"  placeholder="Внеси име"  autoFocus value={testNameField} onChange={(event) => setTestName(event.target.value)}/>
                                    <label htmlFor="name">Име</label>
                                </div>
                                <button className="btn btn-primary btn-sm m-2" type="submit" onClick={addNewTest}>Зачувај</button>
                                <button type="button" className="btn btn-secondary btn-sm" onClick={goBack}>Откажи</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );
};
export default AddTest;