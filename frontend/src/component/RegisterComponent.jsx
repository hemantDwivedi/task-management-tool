import { useState } from "react"
import { Container, Row, Col } from 'react-bootstrap';
import { registerApi } from "../service/AuthApiService"
import { useNavigate } from 'react-router-dom'

const RegisterComponent = () => {

    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const [errors, setErrors] = useState({
        username: '',
        email: '',
        password: ''
    })


    function handleRegistrationForm(event) {
        event.preventDefault()

        if (validateForm()) {
            const register = { username, email, password }

            registerApi(register)
                .then(response => {
                    console.log(response.data);
                    navigate('/login')
                })
                .catch(error => console.error(error))
        }

    }

    function validateForm() {
        let valid = true

        const errorsCopy = { ...errors }

        if (username.trim()) {
            errorsCopy.username = ''
        } else {
            errorsCopy.username = 'username required'
            valid = false
        }

        if (email.trim()) {
            errorsCopy.email = ''
        } else {
            errorsCopy.email = 'email required'
            valid = false
        }

        if (password.trim()) {
            errorsCopy.password = ''
        } else {
            errorsCopy.password = 'password required'
            valid = false
        }

        setErrors(errorsCopy)

        return valid
    }

    return (
        <div className="center-in-page">
            <Container>
                <Row className="justify-content-center align-items-center">
                    <Col lg={3}>
                        <form className="bg-light shadow-lg p-4">
                            <div className="form-group mb-2">
                                <input
                                    type="text"
                                    name="username"
                                    className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                    placeholder="Username"
                                    value={username}
                                    onChange={(event) => setUsername(event.target.value)}
                                />
                                {errors.username && <div className="invalid-feedback">{errors.username}</div>}
                            </div>
                            <div className="form-group mb-2">
                                <input
                                    type="text"
                                    name="email"
                                    className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                    placeholder="Email"
                                    value={email}
                                    onChange={(event) => setEmail(event.target.value)}
                                />
                                {errors.email && <div className="invalid-feedback">{errors.email}</div>}

                            </div>
                            <div className="form-group">
                                <input
                                    type="password"
                                    name="password"
                                    className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                    placeholder="Password"
                                    value={password}
                                    onChange={(event) => setPassword(event.target.value)}
                                />
                                {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                            </div>
                            <div className="form-group">
                                <button className="btn btn-dark mt-3" onClick={(event) => handleRegistrationForm(event)}>Register</button>
                            </div>
                        </form>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default RegisterComponent