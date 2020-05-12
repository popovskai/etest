import React, {useEffect, useState} from "react";
import axios from "../axios/axios";
import { useParams } from "react-router-dom";
import {Redirect} from "react-router-dom";

const Test = () =>{

    const [testList, setTestList] = useState([]);
    const [userTestId, setUserTestId] = useState([]);
    const [redirectId, setRedirectId] = useState(-1);
    const [testToBeSend, setTest] = useState([]);
    let { id } = useParams();

    useEffect(() =>
    {
        axios.get("http://localhost:8080/api/test/all/"+id)
            .then(response =>
            {
                setTestList(response.data);
            }).catch(error => {
            console.log(error);
        })
    }, []);

    const onDeleteHandler = (testId) => {
        axios.delete("http://localhost:8080/api/test/"+testId)
        let newList = Array.from(testList);
        newList= newList.filter(element => element.id != testId);
        setTestList(newList);
    };
    const startTesting = (test) => {
        axios.post("http://localhost:8080/api/testing/"+test)
            .then(response => {
                setUserTestId(response.data.id);
                setTest(test);
                setRedirectId(userTestId);
                console.log(userTestId);
            }).catch(error => {
            console.log(error);
        })
    };
    return(
        <div>
            <h3>Тестови:</h3>
            <div className="row">
                <div className="col-sm-4 ">
                    <div className="card border-light mt-4">
                        <div className="card-body">
                            <p></p>
                            <a href={"/test/add/"+id}><button type="button" className="btn btn-outline-secondary">Додади нов тест</button></a>
                            <p></p>
                        </div>
                    </div>
                </div>
                {

                    testList.map(test => (
                        <div className="col-sm-4 ">
                            <div className="card border-light mt-4">
                                <div className="card-body">
                                    <h5 className="card-title">{test.name}</h5>
                                    <a href={"/test/"+test.id}><button type="button" className="btn btn-outline-secondary">Погледни тест</button></a>
                                    <button type="button" className="btn btn-outline-secondary" onClick={() => onDeleteHandler(test.id)}>Избриши тест</button>
                                    <button type="button" className="btn btn-success m-2" onClick={() => startTesting(test.id)}>Тестирај се</button>
                                    { redirectId > -1 && <Redirect to={"/testing/" + userTestId +"/"+testToBeSend}/> }
                                </div>
                                <div className="card-footer text-muted">
                                    Последен пат ажуриран на: {test.createdAt}
                                </div>
                            </div>
                        </div>

                    ))
                }

            </div>

        </div>
    );
};
export default Test;