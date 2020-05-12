import React, {useEffect, useState} from "react";
import axios from "../axios/axios";
import { useParams } from "react-router-dom";
import QuestionItem from "../Question/QuestionItem";
import QuestionItemTest from"./QuestionItemTest"
import {Redirect} from "react-router-dom";


const Testing = (props) =>{

    const [testList, setTestList] = useState([]);
    const [userTestId, setUserTestId] = useState([]);
    const [questionList, setQuestionList] = useState([]);
    const [result, setResult] = useState([]);
    const [redirectId, setRedirectId] = useState(-1);
    let {id, testid} = useParams();

    useEffect(() =>
    {
        axios.get("http://localhost:8080/api/question/all/"+testid)
            .then(response =>
            {
                setQuestionList(response.data);
            }).catch(error => {
            console.log(error);
        })
    },[]);

    const checkResult = () => {
        axios.get("http://localhost:8080/api/testing/score?user_testId=" +id).then(response =>{
            setResult(response.data);
            setRedirectId(response.data);
        }).catch(error => {
            console.log(error);
        });
    };

    return(
        <div>
            <h3>Прашања:</h3>
            <ol>
                {

                    questionList.map(question => (

                        <div className="col-sm-8 ">
                            <div className="col-sm-12 ">
                                <div className="card border-light mt-4">
                                    <div className="card-body">
                                        <li><QuestionItemTest id={id} question_id={question.id} content={question.content}/></li>
                                    </div>
                                    { redirectId > -1 && <Redirect to={"/testing/result/" + result}/> }
                                </div>
                            </div>
                        </div>


                    ))
                }

            </ol>
            <button className="btn btn-primary btn-sm m-2" type="submit" onClick={checkResult}>Крај</button>
        </div>
    );
};
export default Testing;