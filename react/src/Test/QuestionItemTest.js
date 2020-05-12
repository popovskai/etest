import React, {useEffect, useState} from "react";
import axios from "../axios/axios";

const QuestionItemTest = (props) => {

    const [answerList, setAnswerList] = useState([]);
    const [answerField, setAnswerField] = useState([]);
    const [respond, setRespond] = useState(-1);
    const [selectedAnswer, setSelectedAnswer] = useState(-1);

    useEffect(() =>
    {
        axios.get("http://localhost:8080/api/answer/all/"+props.question_id)
            .then(response =>
            {
                setAnswerList(response.data);
                if(response.data.length > 0) {
                    setRespond(response.data[0].id);
                }
            }).catch(error => {
                console.log(error);
            });
    }, []);

    const postRespond = () => {
        axios.post("http://localhost:8080/api/respond/" +props.id+ "?questionId="+props.question_id+"&respondId="+respond).then(response =>{
            console.log(response.data.id);
            setSelectedAnswer(response.data.id);
        }).catch(error => {
            console.log(error);
        });
    };

    return(
        <div>
            <div className="row">
                <div className="col-10">
                    <h5 className="card-title">{props.content}</h5>
                </div>
            </div>
            <br/>
            <select
                onChange={(e) => setRespond(e.target.value)}
                className="browser-default custom-select">
                {
                    answerList.map(answer => (
                        <option value={answer.id} selected={answer.id === respond}>{answer.answerContent}</option>
                    ))
                }
            </select>
            <button className="btn btn-primary btn-sm m-2" type="submit" onClick={postRespond} disabled={selectedAnswer > 0}>Зачувај одговор</button>
        </div>
    );
};
export default QuestionItemTest;