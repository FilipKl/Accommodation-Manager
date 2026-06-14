import { useCallback, useEffect, useState } from 'react';
import userApi from '../api/userApi';
import type { AppUser } from '../api/types/user';

const useUsers = () => {
    const [users, setUsers] = useState<AppUser[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        setLoading(true);
        try {
            const response = await userApi.findAll();
            setUsers(response.data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
        } finally {
            setLoading(false);
        }
    }, []);

    const remove = useCallback(async (id: number) => {
        await userApi.remove(id);
        await fetch();
    }, [fetch]);

    useEffect(() => { void fetch(); }, [fetch]);

    return { users, loading, error, remove };
};

export default useUsers;