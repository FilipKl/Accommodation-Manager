import {
    Box, Button, Card, CardActions, CardContent, CircularProgress,
    Dialog, DialogActions, DialogContent, DialogTitle,
    Grid, TextField, Typography
} from '@mui/material';
import { useState } from 'react';
import useCountries from '../../../hooks/useCountries';
import { useAuth } from '../../../hooks/useAuth';
import type { Country } from '../../../api/types/country';

const emptyForm = { name: '', continent: '' };

const CountriesPage = () => {
    const { countries, loading, create, update, remove } = useCountries();
    const { isAdmin } = useAuth();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [editing, setEditing] = useState<Country | null>(null);
    const [form, setForm] = useState(emptyForm);

    const handleOpen = (country?: Country) => {
        if (country) {
            setEditing(country);
            setForm({ name: country.name, continent: country.continent });
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

    if (loading) return <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}><CircularProgress /></Box>;

    return (
        <Box>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mt: 2, mb: 3 }}>
                <Typography variant='h4'>Countries</Typography>
                {isAdmin() && (
                    <Button variant='contained' onClick={() => handleOpen()}>+ Add New</Button>
                )}
            </Box>

            <Grid container spacing={3}>
                {countries.map(c => (
                    <Grid key={c.id} size={{ xs: 12, sm: 6, md: 4 }}>
                        <Card>
                            <CardContent>
                                <Typography variant='h6'>{c.name}</Typography>
                                <Typography variant='body2' color='text.secondary'>{c.continent}</Typography>
                            </CardContent>
                            {isAdmin() && (
                                <CardActions>
                                    <Button size='small' color='warning' onClick={() => handleOpen(c)}>Edit</Button>
                                    <Button size='small' color='error' onClick={() => remove(c.id)}>Delete</Button>
                                </CardActions>
                            )}
                        </Card>
                    </Grid>
                ))}
            </Grid>

            <Dialog open={dialogOpen} onClose={handleClose} maxWidth='sm' fullWidth>
                <DialogTitle>{editing ? 'Edit Country' : 'Add Country'}</DialogTitle>
                <DialogContent sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 1 }}>
                    <TextField label='Name' value={form.name}
                               onChange={e => setForm(p => ({ ...p, name: e.target.value }))} />
                    <TextField label='Continent' value={form.continent}
                               onChange={e => setForm(p => ({ ...p, continent: e.target.value }))} />
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

export default CountriesPage;