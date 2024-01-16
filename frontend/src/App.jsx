import HeaderComponent from './component/HeaderComponent'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import CreateAccount from './component/CreateAccount'
import LoginComponent from './component/LoginComponent'
import { getLoggedInUserId, isUserLoggedIn } from './service/AuthApiService'
import WelcomePage from './component/Welcome'
import TasksComponent from './component/TasksComponent'
import AddTaskComponent from './component/AddTaskComponent'

function App() {
  const activeUserId = getLoggedInUserId()

  function AuthenticatedRoute({children}){
    const isAuthenticated = isUserLoggedIn()

    if(isAuthenticated){
      return children
    }
    return <Navigate to="/" />
  }

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
      <Routes>
        <Route path='/' element={<WelcomePage />} />
        <Route path='/tasks' element={
        <AuthenticatedRoute>
          <TasksComponent userId={activeUserId} />
        </AuthenticatedRoute>
        } />
        <Route path='/add-task' element={
        <AuthenticatedRoute>
          <AddTaskComponent userId={activeUserId} />
        </AuthenticatedRoute>
        } />
        <Route path='/update-task/:id' element={
        <AuthenticatedRoute>
          <AddTaskComponent userId={activeUserId} />
        </AuthenticatedRoute>
        } />
        <Route path='/create-account' element={<CreateAccount />} />
        <Route path='/login' element={<LoginComponent />} />
      </Routes>
      </BrowserRouter>
    </>
  )
}

export default App