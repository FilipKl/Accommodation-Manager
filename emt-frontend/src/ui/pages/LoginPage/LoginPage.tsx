import { useState } from 'react';
import { Box, Button, Paper, TextField, Typography, Alert } from '@mui/material';
import { useNavigate, Link } from 'react-router';
import authApi from '../../../api/authApi';
import { useAuth } from '../../../hooks/useAuth';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async () => {
        try {
            const response = await authApi.login(username, password);
            login(response.data.token, response.data.username, response.data.role);
            navigate('/');
        } catch {
            setError('Invalid username or password.');
        }
    };

    return (
        <Box sx={{ display: 'flex', justifyContent: 'center', mt: 8 }}>
            <Paper sx={{ p: 4, width: 400 }}>
                <Typography variant='h5' gutterBottom>Login</Typography>
                {error && <Alert severity='error' sx={{ mb: 2 }}>{error}</Alert>}
                <TextField
                    fullWidth label='Username' sx={{ mb: 2 }}
                    value={username} onChange={e => setUsername(e.target.value)}
                />
                <TextField
                    fullWidth label='Password' type='password' sx={{ mb: 2 }}
                    value={password} onChange={e => setPassword(e.target.value)}
                />
                <Button fullWidth variant='contained' onClick={handleSubmit}>Login</Button>
                <Typography sx={{ mt: 2, textAlign: 'center' }}>
                    Don't have an account? <Link to='/register'>Register</Link>
                </Typography>
            </Paper>
        </Box>
    );
};

export default LoginPage;