import { useEffect, useState } from "react"
import { deleteTask, markDone, markPending, retrieveAllTasks } from "../service/TaskApiService"
import { Link, useNavigate } from "react-router-dom"

const TasksComponent = ({ userId }) => {

    const [tasks, setTasks] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        allTasks(userId)
    }, [userId])

    function allTasks(userId) {
        retrieveAllTasks(userId)
            .then(response => setTasks(response.data))
            .catch(error => console.log(error))
    }

    function updateTask(id) {
        navigate(`/update-task/${id}`)
    }

    function deleteTaskFun(id) {
        deleteTask(id)
            .then(() => allTasks(userId))
            .catch(error => console.log(error))
    }

    function markTask(id, IsChecked) {

        console.log(IsChecked);

        if (IsChecked) {
            markDone(id)
                .then(response => {
                    console.log(response.data.id);
                    allTasks(userId);
                })
                .catch(error => console.error(error))
        } else {
            markPending(id)
                .then(response => {
                    console.log(response.data);
                    allTasks(userId);
                })
                .catch(error => console.error(error))
        }
    }

    return (
        <div>
            <div className="container w-50 mt-5 py-5">
                <div className="row overflow-auto">
                    {
                        tasks.map(task =>
                            <div key={task.id}>
                                <div className="d-flex justify-content-end mb-2">
                                    <button className="btn" onClick={() => updateTask(task.id)}>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-pencil-square" viewBox="0 0 16 16">
                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                            <path fillRule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
                                        </svg></button>
                                    <button className="btn" onClick={() => deleteTaskFun(task.id)}>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-trash3-fill" viewBox="0 0 16 16">
                                            <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5" />
                                        </svg>
                                    </button>
                                </div>
                                <div className="d-flex gap-3">
                                    <input
                                        className="form-check-input mt-3 border-2 rounded-5 border-dark fs-5 mb-0"
                                        onChange={e => markTask(task.id, e.target.checked)}
                                        type="checkbox"
                                    />
                                    <div
                                        // className="w-100 bg-light mb-2 p-3"
                                        className={`mb-2 w-100 p-3 ${task.completed ? 'text-decoration-line-through text-secondary' : 'bg-light'}`}
                                    >{task.task}</div>
                                </div>
                            </div>
                        )
                    }
                </div>
                <Link to='/add-task' className="btn btn-sm btn-outline-dark mt-3">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-plus" viewBox="0 0 20 20">
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4" />
                    </svg> Add task</Link>
            </div>
        </div>
    )
}

export default TasksComponent