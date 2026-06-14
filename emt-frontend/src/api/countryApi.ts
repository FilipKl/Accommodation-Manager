import axiosInstance from '../axios/axios';
import type { Country } from './types/country';

const countryApi = {
    findAll: async () =>
        axiosInstance.get<Country[]>('/countries'),

    create: async (data: Omit<Country, 'id'>) =>
        axiosInstance.post<Country>('/countries/add', data),

    update: async (id: number, data: Omit<Country, 'id'>) =>
        axiosInstance.put<Country>(`/countries/${id}/edit`, data),

    remove: async (id: number) =>
        axiosInstance.delete<Country>(`/countries/${id}/delete`)
};

export default countryApi;