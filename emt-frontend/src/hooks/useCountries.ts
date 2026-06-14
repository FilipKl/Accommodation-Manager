import { useCallback, useEffect, useState } from 'react';
import countryApi from '../api/countryApi';
import type { Country } from '../api/types/country';

const useCountries = () => {
    const [countries, setCountries] = useState<Country[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        setLoading(true);
        try {
            const response = await countryApi.findAll();
            setCountries(response.data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
        } finally {
            setLoading(false);
        }
    }, []);

    const create = useCallback(async (data: Omit<Country, 'id'>) => {
        await countryApi.create(data);
        await fetch();
    }, [fetch]);

    const update = useCallback(async (id: number, data: Omit<Country, 'id'>) => {
        await countryApi.update(id, data);
        await fetch();
    }, [fetch]);

    const remove = useCallback(async (id: number) => {
        await countryApi.remove(id);
        await fetch();
    }, [fetch]);

    useEffect(() => { void fetch(); }, [fetch]);

    return { countries, loading, error, fetch, create, update, remove };
};

export default useCountries;