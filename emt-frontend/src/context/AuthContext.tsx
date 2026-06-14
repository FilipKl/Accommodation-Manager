import { createContext, useState } from 'react';
import type { ReactNode } from 'react';

export interface UserPayload {
    username: string;
    role: string;
}

export interface AuthContextType {
    user: UserPayload | null;
    login: (token: string, username: string, role: string) => void;
    logout: () => void;
    isAuthenticated: () => boolean;
    isAdmin: () => boolean;
}

export const AuthContext = createContext<AuthContextType | null>(null); // eslint-disable-line react-refresh/only-export-components

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const [user, setUser] = useState<UserPayload | null>(() => {
        const token = localStorage.getItem('token');
        const username = localStorage.getItem('username');
        const role = localStorage.getItem('role');

        if (token && username && role) {
            return { username, role };
        }
        return null;
    });

    const login = (token: string, username: string, role: string) => {
        localStorage.setItem('token', token);
        localStorage.setItem('username', username);
        localStorage.setItem('role', role);
        setUser({ username, role });
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('username');
        localStorage.removeItem('role');
        setUser(null);
    };

    const isAuthenticated = () => user !== null;
    const isAdmin = () => user?.role === 'ROLE_ADMINISTRATOR';

    return (
        <AuthContext.Provider value={{ user, login, logout, isAuthenticated, isAdmin }}>
            {children}
        </AuthContext.Provider>
    );
};
