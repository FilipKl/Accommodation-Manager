import axiosInstance from '../axios/axios';
import type { AuthResponse } from './types/auth';

const authApi = {
    login: async (username: string, password: string) =>
        axiosInstance.post<AuthResponse>('/user/login', { username, password }),

    register: async (name: string, surname: string, email: string,
                     username: string, password: string) =>
        axiosInstance.post<AuthResponse>('/user/register', {
            name, surname, email, username, password
        })
};

export default authApi;