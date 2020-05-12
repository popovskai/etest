import React, {useEffect, useState} from 'react';
import axios from "../axios/axios";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons'

const QuestionItem = props => {

    const [answerList, setAnswerList] = useState([]);
    const [answerField, setAnswerField] = useState([]);
    const [updateValue, setUpdateValue] = useState("");
    const [rightAnswer, setRightAnswer] = useState("");
    const [ddown, setDdown] = useState(0);

    const element = <FontAwesomeIcon icon={faTrashAlt} />

    const addNewAnswer = () =>
    {
        axios.post("http://localhost:8080/api/answer/" + props.id, {answerContent: answerField}).then(response => {
            setUpdateValue(updateValue + 1);
            setAnswerField("");
        })

            .catch(error => {
                console.log(error);
            })
    };
    const addRightAnswer = () =>
    {
        axios.post("http://localhost:8080/api/answer/right/" + props.id, {id: ddown}).then(response => {
            console.log(response);
            setUpdateValue(updateValue + 1);
            setAnswerField("");
        }).catch(error => {
                console.log(error);
            });
    };

    useEffect(() =>
    {
        axios.get("http://localhost:8080/api/answer/all/"+props.id)
            .then(response =>
            {
                setAnswerList(response.data);


                axios.get("http://localhost:8080/api/question/right/" + props.id).then(response2 =>
                {
                    let answer = response2.data;
                    if(answer) {
                        setRightAnswer(response2.data);
                    }
                }).catch(error => {
                    if(response.data.length > 0) {
                        setDdown(response.data[0].id);
                    }
                    console.log(error);
                });
            }).catch(error => {
            console.log(error);
        });



    }, [updateValue]);

    const onDeleteHandler = (answerId) => {
        axios.delete("http://localhost:8080/api/answer/"+answerId)
        let newList = Array.from(answerList);
        newList= newList.filter(element => element.id != answerId);
        setAnswerList(newList);
    };

    const DeleteQuestion = (questionId) => {
        axios.delete("http://localhost:8080/api/question/"+questionId)
    };
    return (
        <div>
                    <div className="row">
                        <div className="col-10">
                        <h5 className="card-title">{props.content}</h5>
                        </div>
                    </div>
                    <br/>
                    <ol type="A" className="list-group">

        {

            answerList.map(answer => (


                <li>{answer.answerContent} <a href="#" onClick={() => onDeleteHandler(answer.id)}> {element} </a> </li>



            ))
        }
                    <li><div className="form-label-group">
                        <input className="form-control" type="text"  placeholder="Додади одговор"  autoFocus value={answerField} onChange={(event) => setAnswerField(event.target.value)}/>
                        <button className="btn btn-primary btn-sm m-2" type="submit" onClick={addNewAnswer}>Додади</button>
                        </div></li>
                    </ol>
            <div>
                <p>Точен одговор е <b><span style={{color:"#50c878"}}>{rightAnswer.answerContent} </span></b></p>
                <select
                    onChange={e => setDdown(e.currentTarget.value)}
                    className="browser-default custom-select">
                    {
                        answerList.map(answer => (
                            <option value={answer.id} selected={answer.id === ddown}>{answer.answerContent}</option>
                        ))
                    }
                </select>
                <button className="btn btn-primary btn-sm m-2" type="submit" onClick={addRightAnswer}>Промени точен одговор</button>
            </div>
        </div>
    );
};

export default QuestionItem;