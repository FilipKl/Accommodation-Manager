import { Box, Typography } from '@mui/material';

const HomePage = () => {
    return (
        <Box sx={{ mt: 3 }}>
            <Typography variant='h4' gutterBottom>
                Welcome to Accommodation App! 🏠
            </Typography>
            <Typography variant='body1'>
                Browse accommodations, hosts and countries using the navigation above.
            </Typography>
        </Box>
    );
};

export default HomePage;