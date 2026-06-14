import { BrowserRouter, Route, Routes } from 'react-router';
import { AuthProvider } from './context/AuthContext';
import Layout from './ui/components/layout/Layout/Layout';
import HomePage from './ui/pages/HomePage/HomePage';
import AccommodationsPage from './ui/pages/AccommodationsPage/AccommodationsPage';
import AccommodationDetailsPage from './ui/pages/AccommodationDetailsPage/AccommodationDetailsPage';
import HostsPage from './ui/pages/HostsPage/HostsPage';
import CountriesPage from './ui/pages/CountriesPage/CountriesPage';
import LoginPage from './ui/pages/LoginPage/LoginPage';
import RegisterPage from './ui/pages/RegisterPage/RegisterPage';
import ProtectedRoute from './ui/components/ProtectedRoute';
import UsersPage from './ui/pages/UsersPage/UsersPage';

function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<Layout />}>
                        <Route index element={<HomePage />} />
                        <Route path='login' element={<LoginPage />} />
                        <Route path='register' element={<RegisterPage />} />
                        <Route path='accommodations' element={
                            <ProtectedRoute><AccommodationsPage /></ProtectedRoute>
                        } />
                        <Route path='accommodations/:id' element={
                            <ProtectedRoute><AccommodationDetailsPage /></ProtectedRoute>
                        } />
                        <Route path='hosts' element={
                            <ProtectedRoute><HostsPage /></ProtectedRoute>
                        } />
                        <Route path='countries' element={
                            <ProtectedRoute><CountriesPage /></ProtectedRoute>
                        } />
                        <Route path='users' element={
                            <ProtectedRoute requireAdmin><UsersPage /></ProtectedRoute>
                        } />
                    </Route>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;