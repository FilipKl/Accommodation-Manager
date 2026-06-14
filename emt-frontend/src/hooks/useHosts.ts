import { useCallback, useEffect, useState } from 'react';
import hostApi from '../api/hostApi';
import type { Host } from '../api/types/host';

const useHosts = () => {
    const [hosts, setHosts] = useState<Host[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        setLoading(true);
        try {
            const response = await hostApi.findAll();
            setHosts(response.data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
        } finally {
            setLoading(false);
        }
    }, []);

    const create = useCallback(async (data: Omit<Host, 'id' | 'countryName'>) => {
        await hostApi.create(data);
        await fetch();
    }, [fetch]);

    const update = useCallback(async (id: number, data: Omit<Host, 'id' | 'countryName'>) => {
        await hostApi.update(id, data);
        await fetch();
    }, [fetch]);

    const remove = useCallback(async (id: number) => {
        await hostApi.remove(id);
        await fetch();
    }, [fetch]);

    useEffect(() => { void fetch(); }, [fetch]);

    return { hosts, loading, error, fetch, create, update, remove };
};

export default useHosts;