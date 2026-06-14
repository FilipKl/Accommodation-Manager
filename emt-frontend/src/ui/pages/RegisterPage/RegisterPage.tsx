import { useState } from 'react';
import { Box, Button, Paper, TextField, Typography, Alert } from '@mui/material';
import { useNavigate, Link } from 'react-router';
import authApi from '../../../api/authApi';
import { useAuth } from '../../../hooks/useAuth';

const RegisterPage = () => {
    const [form, setForm] = useState({ name: '', surname: '', email: '', username: '', password: '' });
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleChange = (field: string) => (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm(prev => ({ ...prev, [field]: e.target.value }));
    };

    const handleSubmit = async () => {
        try {
            const response = await authApi.register(
                form.name, form.surname, form.email, form.username, form.password
            );
            login(response.data.token, response.data.username, response.data.role);
            navigate('/');
        } catch {
            setError('Registration failed. Please try again.');
        }
    };

    return (
        <Box sx={{ display: 'flex', justifyContent: 'center', mt: 8 }}>
            <Paper sx={{ p: 4, width: 400 }}>
                <Typography variant='h5' gutterBottom>Register</Typography>
                {error && <Alert severity='error' sx={{ mb: 2 }}>{error}</Alert>}
                {['name', 'surname', 'email', 'username', 'password'].map(field => (
                    <TextField
                        key={field} fullWidth
                        label={field.charAt(0).toUpperCase() + field.slice(1)}
                        type={field === 'password' ? 'password' : 'text'}
                        sx={{ mb: 2 }}
                        value={form[field as keyof typeof form]}
                        onChange={handleChange(field)}
                    />
                ))}
                <Button fullWidth variant='contained' onClick={handleSubmit}>Register</Button>
                <Typography sx={{ mt: 2, textAlign: 'center' }}>
                    Already have an account? <Link to='/login'>Login</Link>
                </Typography>
            </Paper>
        </Box>
    );
};

export default RegisterPage;