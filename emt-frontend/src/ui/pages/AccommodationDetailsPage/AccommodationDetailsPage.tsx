import { Box, Breadcrumbs, Chip, CircularProgress, Paper, Stack, Typography } from '@mui/material';
import { Link, useParams } from 'react-router';
import useAccommodationDetails from '../../../hooks/useAccommodationDetails';

const AccommodationDetailsPage = () => {
    const { id } = useParams();
    const { accommodation } = useAccommodationDetails(id);

    if (!accommodation) return <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}><CircularProgress /></Box>;

    return (
        <Box>
            <Breadcrumbs sx={{ mb: 3 }}>
                <Link to='/accommodations' style={{ textDecoration: 'none', color: 'inherit' }}>
                    Accommodations
                </Link>
                <Typography color='text.primary'>{accommodation.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{ p: 4, borderRadius: 4 }}>
                <Typography variant='h3' gutterBottom>{accommodation.name}</Typography>
                <Typography variant='body1' sx={{ mb: 1 }}>Host: {accommodation.hostFullName}</Typography>
                <Typography variant='body1' sx={{ mb: 1 }}>Rooms: {accommodation.numRooms}</Typography>
                <Typography variant='body1' sx={{ mb: 2 }}>Condition: {accommodation.condition}</Typography>
                <Stack direction='row' spacing={1}>
                    <Chip label={accommodation.category} color='primary' variant='outlined' />
                    <Chip label={accommodation.rented ? 'Rented' : 'Available'}
                          color={accommodation.rented ? 'error' : 'success'} />
                </Stack>
            </Paper>
        </Box>
    );
};

export default AccommodationDetailsPage;