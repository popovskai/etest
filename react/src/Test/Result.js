import React, {useEffect, useState} from "react";
import axios from "../axios/axios";
import { useParams } from "react-router-dom";

const Result = () =>{

    let { id } = useParams();

    return(
        <div className="display-4">
        <span className="badge badge-pill badge-info">Освоивте: {id} поени</span>
        </div>
    );
};
export default Result;