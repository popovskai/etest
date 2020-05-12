import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import Category from "./Category/Category";
import AddCategory from "./Category/AddCategory";
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Header from "./Header/Header";
import Test from "./Test/Test";
import AddTest from "./Test/AddTest";
import TestView from "./Test/TestView";
import Testing from "./Test/Testing";
import Login from "./Login/Login";
import Result from "./Test/Result"

import axios from './axios/axios';
import Cookies from 'universal-cookie';

function App() {
    let cookies = new Cookies();
    let cookieJwt = cookies.get("jwt");
    axios.defaults.headers.common["Authorization"] = cookieJwt;

    const [logged, setLogged] = useState(cookieJwt != undefined);

    const onLoginHandler = (jwtToken) =>
    {
        cookies.set("jwt", jwtToken);
        axios.defaults.headers.common["Authorization"] = jwtToken;
        setLogged(true);
    };

  return (

    <div className="App">
        <div className="container-lg">
        <Header logged={logged}/>
        <Router>
            {logged === true ?
                <Switch>
                    <Route path={"/category"} exact render={() =>
                        <Category/>}>
                    </Route>
                    <Route path={"/category/add"} exact render={() =>
                        <AddCategory/>}>
                    </Route>
                    <Route path={"/category/test/:id"} exact render={() =>
                        <Test/>}>
                    </Route>
                    <Route path={"/test/:id"} exact render={() =>
                        <TestView/>}>
                    </Route>
                    <Route path={"/test/add/:id"} exact render={() =>
                        <AddTest/>}>
                    </Route>
                    <Route path={"/testing/result/:id"} exact render={() =>
                        <Result/>}>
                    </Route>
                    <Route path={"/testing/:id/:testid"} exact render={() =>
                        <Testing/>}>
                    </Route>
                </Switch>
                :
                <h3>
                <span className="badge badge-danger">Ве молиме најавете се</span>
                </h3>
            }

            <Route path={"/login"} exact render={()=>
                <Login onLogin={onLoginHandler}/>}>
            </Route>
        </Router>
        </div>
    </div>
  );
}

export default App;
