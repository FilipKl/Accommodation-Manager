import { Box, Container } from '@mui/material';
import { Outlet } from 'react-router';
import Header from '../Header/Header';

const Layout = () => {
    return (
        <Box>
            <Header />
            <Container sx={{ my: 2 }} maxWidth='lg'>
                <Outlet />
            </Container>
        </Box>
    );
};

export default Layout;