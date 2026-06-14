import axiosInstance from '../axios/axios';
import type { AppUser } from './types/user';

const userApi = {
    findAll: async () => axiosInstance.get<AppUser[]>('/user'),
    remove: async (id: number) => axiosInstance.delete(`/user/${id}/delete`)
};

export default userApi;