import { useCallback, useEffect, useState } from 'react';
import accommodationApi from '../api/accommodationApi';
import type { Accommodation } from '../api/types/accommodation';

const useAccommodations = () => {
    const [accommodations, setAccommodations] = useState<Accommodation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        setLoading(true);
        try {
            const response = await accommodationApi.findAll();
            setAccommodations(response.data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
        } finally {
            setLoading(false);
        }
    }, []);

    const create = useCallback(async (data: Omit<Accommodation, 'id' | 'hostFullName' | 'rented'>) => {
        await accommodationApi.create(data);
        await fetch();
    }, [fetch]);

    const update = useCallback(async (id: number, data: Omit<Accommodation, 'id' | 'hostFullName' | 'rented'>) => {
        await accommodationApi.update(id, data);
        await fetch();
    }, [fetch]);

    const remove = useCallback(async (id: number) => {
        await accommodationApi.remove(id);
        await fetch();
    }, [fetch]);

    const markAsRented = useCallback(async (id: number) => {
        await accommodationApi.markAsRented(id);
        await fetch();
    }, [fetch]);

    useEffect(() => { void fetch(); }, [fetch]);

    return { accommodations, loading, error, fetch, create, update, remove, markAsRented };
};

export default useAccommodations;