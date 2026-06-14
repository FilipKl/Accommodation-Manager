import {
    Box, Button, Card, CardActions, CardContent, Chip, CircularProgress,
    Dialog, DialogActions, DialogContent, DialogTitle, Grid,
    MenuItem, Stack, TextField, Typography
} from '@mui/material';
import { useState } from 'react';
import useAccommodations from '../../../hooks/useAccommodations';
import useHosts from '../../../hooks/useHosts';
import { useAuth } from '../../../hooks/useAuth';
import type { Accommodation } from '../../../api/types/accommodation';
import { useNavigate } from 'react-router';

const CATEGORIES = ['ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL'];
const CONDITIONS = ['GOOD', 'BAD'];

const emptyForm = { name: '', category: '', condition: '', hostId: 0, numRooms: 1 };

const AccommodationsPage = () => {
    const { accommodations, loading, create, update, remove } = useAccommodations();
    const { hosts } = useHosts();
    const { isAdmin } = useAuth();
    const navigate = useNavigate();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [editing, setEditing] = useState<Accommodation | null>(null);
    const [form, setForm] = useState(emptyForm);

    const handleOpen = (accommodation?: Accommodation) => {
        if (accommodation) {
            setEditing(accommodation);
            setForm({
                name: accommodation.name,
                category: accommodation.category,
                condition: accommodation.condition,
                hostId: accommodation.hostId,
                numRooms: accommodation.numRooms
            });
        } else {
            setEditing(null);
            setForm(emptyForm);
        }
        setDialogOpen(true);
    };

    const handleClose = () => {
        setDialogOpen(false);
        setEditing(null);
        setForm(emptyForm);
    };

    const handleSubmit = async () => {
        if (editing) {
            await update(editing.id, form);
        } else {
            await create(form);
        }
        handleClose();
    };

    const handleDelete = async (id: number) => {
        await remove(id);
    };

    if (loading) return <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}><CircularProgress /></Box>;

    return (
        <Box>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mt: 2, mb: 3 }}>
                <Typography variant='h4'>Accommodations</Typography>
                {isAdmin() && (
                    <Button variant='contained' onClick={() => handleOpen()}>+ Add New</Button>
                )}
            </Box>

            <Grid container spacing={3}>
                {accommodations.map(a => (
                    <Grid key={a.id} size={{ xs: 12, sm: 6, md: 4 }}>
                        <Card sx={{ cursor: 'pointer' }}>
                            <CardContent onClick={() => navigate(`/accommodations/${a.id}`)}>
                                <Typography variant='h6'>{a.name}</Typography>
                                <Typography variant='body2' color='text.secondary'>Host: {a.hostFullName}</Typography>
                                <Typography variant='body2'>Rooms: {a.numRooms}</Typography>
                                <Stack direction='row' spacing={1} sx={{ mt: 1 }}>
                                    <Chip label={a.category} size='small' color='primary' variant='outlined' />
                                    <Chip label={a.rented ? 'Rented' : 'Available'} size='small'
                                          color={a.rented ? 'error' : 'success'} />
                                </Stack>
                            </CardContent>
                            {isAdmin() && (
                                <CardActions>
                                    <Button size='small' color='warning' onClick={() => handleOpen(a)}>Edit</Button>
                                    <Button size='small' color='error' onClick={() => handleDelete(a.id)}>Delete</Button>
                                </CardActions>
                            )}
                        </Card>
                    </Grid>
                ))}
            </Grid>

            <Dialog open={dialogOpen} onClose={handleClose} maxWidth='sm' fullWidth>
                <DialogTitle>{editing ? 'Edit Accommodation' : 'Add Accommodation'}</DialogTitle>
                <DialogContent sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 1 }}>
                    <TextField label='Name' value={form.name}
                               onChange={e => setForm(p => ({ ...p, name: e.target.value }))} />
                    <TextField select label='Category' value={form.category}
                               onChange={e => setForm(p => ({ ...p, category: e.target.value }))}>
                        {CATEGORIES.map(c => <MenuItem key={c} value={c}>{c}</MenuItem>)}
                    </TextField>
                    <TextField select label='Condition' value={form.condition}
                               onChange={e => setForm(p => ({ ...p, condition: e.target.value }))}>
                        {CONDITIONS.map(c => <MenuItem key={c} value={c}>{c}</MenuItem>)}
                    </TextField>
                    <TextField select label='Host' value={form.hostId}
                               onChange={e => setForm(p => ({ ...p, hostId: Number(e.target.value) }))}>
                        {hosts.map(h => (
                            <MenuItem key={h.id} value={h.id}>{h.name} {h.surname}</MenuItem>
                        ))}
                    </TextField>
                    <TextField label='Number of Rooms' type='number' value={form.numRooms}
                               onChange={e => setForm(p => ({ ...p, numRooms: Number(e.target.value) }))} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button variant='contained' onClick={handleSubmit}>
                        {editing ? 'Update' : 'Create'}
                    </Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
};

export default AccommodationsPage;