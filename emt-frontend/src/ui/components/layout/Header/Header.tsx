import {
    AppBar, Box, Button, Drawer, IconButton, List, ListItem,
    ListItemButton, ListItemText, Toolbar, Typography
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import { Link, useNavigate } from 'react-router';
import { useState } from 'react';
import { useAuth } from '../../../../hooks/useAuth';

const pages = [
    { path: '/', name: 'Home', adminOnly: false },
    { path: '/accommodations', name: 'Accommodations', adminOnly: false },
    { path: '/hosts', name: 'Hosts', adminOnly: false },
    { path: '/countries', name: 'Countries', adminOnly: false },
    { path: '/users', name: 'Users', adminOnly: true }
];

const Header = () => {
    const [drawerOpen, setDrawerOpen] = useState(false);
    const { user, logout, isAuthenticated, isAdmin } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    };

    return (
        <Box>
            <AppBar position='static'>
                <Toolbar>
                    <IconButton
                        size='large' edge='start' color='inherit'
                        sx={{ mr: 2, display: { md: 'none' } }}
                        onClick={() => setDrawerOpen(true)}
                    >
                        <MenuIcon />
                    </IconButton>

                    <Typography variant='h6' sx={{ mr: 3 }}>
                        ACCOMMODATION APP
                    </Typography>

                    <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                        {pages
                            .filter(page => !page.adminOnly || isAdmin())
                            .map((page) => (
                                <Link key={page.name} to={page.path}>
                                    <Button sx={{ my: 2, color: 'white' }}>{page.name}</Button>
                                </Link>
                            ))}
                    </Box>

                    <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                        {isAuthenticated() ? (
                            <>
                                <Typography variant='body2' sx={{ color: 'white' }}>
                                    {user?.username} ({user?.role === 'ROLE_ADMINISTRATOR' ? 'Admin' : 'User'})
                                </Typography>
                                <Button color='inherit' onClick={handleLogout}>Logout</Button>
                            </>
                        ) : (
                            <>
                                <Button color='inherit' component={Link} to='/login'>Login</Button>
                                <Button color='inherit' component={Link} to='/register'>Register</Button>
                            </>
                        )}
                    </Box>
                </Toolbar>
            </AppBar>

            <Drawer anchor='left' open={drawerOpen} onClose={() => setDrawerOpen(false)}>
                <Box sx={{ width: 240 }} onClick={() => setDrawerOpen(false)}>
                    <List>
                        {pages
                            .filter(page => !page.adminOnly || isAdmin())
                            .map((page) => (
                                <ListItem key={page.name} disablePadding>
                                    <ListItemButton component={Link} to={page.path}>
                                        <ListItemText primary={page.name} />
                                    </ListItemButton>
                                </ListItem>
                            ))}
                    </List>
                </Box>
            </Drawer>
        </Box>
    );
};

export default Header;