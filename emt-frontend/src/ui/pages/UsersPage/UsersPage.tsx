import {
    Box, Button, Card, CardActions, CardContent,
    Chip, CircularProgress, Grid, Typography
} from '@mui/material';
import useUsers from '../../../hooks/useUsers';
import { useAuth } from '../../../hooks/useAuth';

const UsersPage = () => {
    const { users, loading, remove } = useUsers();
    const { user: currentUser } = useAuth();

    if (loading) return (
        <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
            <CircularProgress />
        </Box>
    );

    return (
        <Box>
            <Typography variant='h4' gutterBottom sx={{ mt: 2, mb: 3 }}>Users</Typography>
            <Grid container spacing={3}>
                {users.map(u => (
                    <Grid key={u.id} size={{ xs: 12, sm: 6, md: 4 }}>
                        <Card>
                            <CardContent>
                                <Typography variant='h6'>{u.name} {u.surname}</Typography>
                                <Typography variant='body2' color='text.secondary'>
                                    @{u.username}
                                </Typography>
                                <Typography variant='body2' color='text.secondary'>
                                    {u.email}
                                </Typography>
                                <Chip
                                    label={u.role === 'ROLE_ADMINISTRATOR' ? 'Admin' : 'User'}
                                    color={u.role === 'ROLE_ADMINISTRATOR' ? 'error' : 'primary'}
                                    size='small'
                                    sx={{ mt: 1 }}
                                />
                            </CardContent>
                            <CardActions>
                                <Button
                                    size='small'
                                    color='error'
                                    disabled={u.username === currentUser?.username}
                                    onClick={() => remove(u.id)}
                                >
                                    {u.username === currentUser?.username ? 'Current User' : 'Delete'}
                                </Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
};

export default UsersPage;