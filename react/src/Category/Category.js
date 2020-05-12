import React, {useState, useEffect} from "react";
import axios from '../axios/axios';
import {Link} from "react-router-dom";
import '../App.css';


const Category = () =>{

    const [categoryList, setCategoryList] = useState([]);

    useEffect(() =>
    {
        axios.get("http://localhost:8080/api/category/all")
            .then(response =>
            {
                setCategoryList(response.data);
            });
    }, []);

    const onDeleteHandler = (id) => {
        axios.delete("http://localhost:8080/api/category/"+id)
        let newList = Array.from(categoryList);
        newList= newList.filter(element => element.id != id);
        setCategoryList(newList);
    };
    return(
        <div className="container">
            <h3>Категории:</h3>
            <div className="row">
                <div className="col-md-4 ">
                    <div className="card border-light mt-4">
                        <div className="card-body">
                            <a href={"/category/add"}><button type="button" className="btn btn-outline-secondary">Додади нова категорија</button></a>

                        </div>
                    </div>
                </div>
            {

                categoryList.map(category => (
                        <div className="col-sm-4 ">
                            <div className="card border-light mt-4">
                                <div className="card-body">
                                    <h5 className="card-title">{category.name}</h5>
                                    <a href={"/category/test/"+category.id}><button type="button" className="btn btn-outline-secondary ">Погледни тестови ({category.tests.length}) </button></a>
                                    <button type="button" className="btn btn-outline-secondary" onClick={() => onDeleteHandler(category.id)}>Избриши категорија</button>
                                </div>
                            </div>
                         </div>

                ))
            }

            </div>

        </div>
    );
};
export default Category;