import axios from "axios";
import { getBasicAuth } from "./AuthApiService";

const API_BASE_URL = 'http://localhost:8080/api/v1/tasks'

axios.interceptors.request.use(config => {
    config.headers['Authorization'] = getBasicAuth();
    return config;
}, error => {
    return Promise.reject(error);
});

export const retrieveAllTasks = (id) => axios.get(API_BASE_URL + '/user/' + id)

export const createTask = (task, userId) => axios.post(API_BASE_URL + '/user/' + userId, task)

export const retrieveTaskById = (taskId) => axios.get(API_BASE_URL + '/' + taskId)

export const updateTask = (task, id) => axios.put(API_BASE_URL + '/' + id, task)

export const deleteTask = (id) => axios.delete(API_BASE_URL + '/' + id)

export const markDone = (id) => axios.patch(API_BASE_URL + '/' + id + '/task-done')

export const markPending = (id) => axios.patch(API_BASE_URL + '/' + id + '/task-pending')