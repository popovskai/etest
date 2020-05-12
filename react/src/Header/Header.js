import React from 'react';

const Header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-light bg-transparent">
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <a className="nav-item nav-link" href={"/category"}>Категории</a>
                    </div>
                </div>
                { !props.logged &&
                <form className="form-inline mt-2 mt-md-0 ml-3">
                    <a href={"/login"} className="btn btn-outline-info my-2 my-sm-0">Најави се</a>
                    <a href="#" className="btn btn-outline-info my-2 my-sm-0">Регистрирај се</a>
                </form> }
            </nav>
        </header>
    )
}

export default Header;