import React from 'react'
import logo from '../assets/logo.svg'
import { NavLink, useNavigate } from "react-router-dom"
import { isUserLoggedIn, logout } from '../service/AuthApiService'

const HeaderComponent = () => {

    const isAuth = isUserLoggedIn()
    const navigate = useNavigate()

    function handleLogout() {
        logout()
        navigate('/login')
    }

    return (
        <div>
            <nav className="fixed-top navbar navbar-expand-lg navbar-light" style={{backgroundColor: "#9400FF"}}>
                <div className="container">
                    <a className="navbar-brand fw-bold">
                        <img src={logo} alt="logo" width={30} height={30} />
                    </a>
                    <ul className="navbar-nav">
                        {
                            !isAuth &&

                            <li className="nav-item">
                                <NavLink className='nav-link text-light' to='/create-account'>Create account</NavLink>
                            </li>
                        }
                        {
                            !isAuth &&
                            <li className="nav-item">
                                <NavLink className='nav-link text-light' to='/login'>Login</NavLink>
                            </li>
                        }
                        {
                            isAuth &&
                            <li className="nav-item">
                                <NavLink className='nav-link text-light' to='/login' onClick={handleLogout}>Logout</NavLink>
                            </li>
                        }
                    </ul>
                </div>
            </nav>
        </div>
    )
}

export default HeaderComponent