import axiosInstance from '../axios/axios';
import type { Accommodation } from './types/accommodation';

const accommodationApi = {
    findAll: async () =>
        axiosInstance.get<Accommodation[]>('/accommodations'),

    findById: async (id: string) =>
        axiosInstance.get<Accommodation>(`/accommodations/${id}`),

    create: async (data: Omit<Accommodation, 'id' | 'hostFullName' | 'rented'>) =>
        axiosInstance.post<Accommodation>('/accommodations/add', data),

    update: async (id: number, data: Omit<Accommodation, 'id' | 'hostFullName' | 'rented'>) =>
        axiosInstance.put<Accommodation>(`/accommodations/${id}/edit`, data),

    remove: async (id: number) =>
        axiosInstance.delete<Accommodation>(`/accommodations/${id}/delete`),

    markAsRented: async (id: number) =>
        axiosInstance.patch<Accommodation>(`/accommodations/${id}/rent`)
};

export default accommodationApi;