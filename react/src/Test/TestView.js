import React, {useEffect, useState} from "react";
import axios from "../axios/axios";
import { useParams } from "react-router-dom";
import QuestionItem from "../Question/QuestionItem"


const Test = props =>{

    const [questionList, setQuestionList] = useState([]);
    const [questionContentField, setQuestionField] = useState("");
    const [updateValue, setUpdateValue] = useState("");
    let { id } = useParams();
    const addNewQuestion = () =>
    {
        axios.post("http://localhost:8080/api/question/" + id, {content: questionContentField}).then(response => {
            setUpdateValue(updateValue + 1);
            setQuestionField("");
        })

            .catch(error => {
                console.log(error);
            })
    };

    const DeleteQuestion = (questionId) => {
        axios.delete("http://localhost:8080/api/question/"+questionId)
        let newList = Array.from(questionList);
        newList= newList.filter(element => element.id != questionId);
        setQuestionList(newList);
    };

    useEffect(() =>
    {
        axios.get("http://localhost:8080/api/question/all/"+id)
            .then(response =>
            {
                setQuestionList(response.data);
            }).catch(error => {
            console.log(error);
        })
    },[updateValue]);

    return(
        <div>
            <h3>Прашања:</h3>
            <ol>
                <div className="col-sm-8 ">
                    <div className="col-sm-12 ">
                        <div className="card border-light mt-4">
                            <div className="card-body">
                                <div className="form-label-group">
                                    <input className="form-control" type="text"  placeholder="Внеси ново прашање"  autoFocus value={questionContentField} onChange={(event) => setQuestionField(event.target.value)}/>
                                    <button className="btn btn-primary btn-sm m-2" type="submit" onClick={addNewQuestion}>Зачувај</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {

                    questionList.map(question => (

                        <div className="col-sm-8 ">
                            <div className="col-sm-12 ">
                                <div className="card border-light mt-4">
                                    <div className="card-body">
                                        <li><QuestionItem id={question.id} content={question.content}/></li>
                                        <div className="row">
                                            <div className="col-4">
                                                <button className="btn btn-danger" onClick={() => DeleteQuestion(question.id)}>Избриши прашање</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    ))
                }

            </ol>
        </div>


    );
};
export default Test;