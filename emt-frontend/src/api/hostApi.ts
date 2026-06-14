import axiosInstance from '../axios/axios';
import type { Host } from './types/host';

const hostApi = {
    findAll: async () =>
        axiosInstance.get<Host[]>('/hosts'),

    findById: async (id: string) =>
        axiosInstance.get<Host>(`/hosts/${id}`),

    create: async (data: Omit<Host, 'id' | 'countryName'>) =>
        axiosInstance.post<Host>('/hosts/add', data),

    update: async (id: number, data: Omit<Host, 'id' | 'countryName'>) =>
        axiosInstance.put<Host>(`/hosts/${id}/edit`, data),

    remove: async (id: number) =>
        axiosInstance.delete<Host>(`/hosts/${id}/delete`)
};

export default hostApi;