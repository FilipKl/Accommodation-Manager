import {
    Box, Button, Card, CardActions, CardContent, CircularProgress,
    Dialog, DialogActions, DialogContent, DialogTitle,
    Grid, MenuItem, TextField, Typography
} from '@mui/material';
import { useState } from 'react';
import useHosts from '../../../hooks/useHosts';
import useCountries from '../../../hooks/useCountries';
import { useAuth } from '../../../hooks/useAuth';
import type { Host } from '../../../api/types/host';

const emptyForm = { name: '', surname: '', countryId: 0 };

const HostsPage = () => {
    const { hosts, loading, create, update, remove } = useHosts();
    const { countries } = useCountries();
    const { isAdmin } = useAuth();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [editing, setEditing] = useState<Host | null>(null);
    const [form, setForm] = useState(emptyForm);

    const handleOpen = (host?: Host) => {
        if (host) {
            setEditing(host);
            setForm({ name: host.name, surname: host.surname, countryId: host.countryId });
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
                <Typography variant='h4'>Hosts</Typography>
                {isAdmin() && (
                    <Button variant='contained' onClick={() => handleOpen()}>+ Add New</Button>
                )}
            </Box>

            <Grid container spacing={3}>
                {hosts.map(h => (
                    <Grid key={h.id} size={{ xs: 12, sm: 6, md: 4 }}>
                        <Card>
                            <CardContent>
                                <Typography variant='h6'>{h.name} {h.surname}</Typography>
                                <Typography variant='body2' color='text.secondary'>Country: {h.countryName}</Typography>
                            </CardContent>
                            {isAdmin() && (
                                <CardActions>
                                    <Button size='small' color='warning' onClick={() => handleOpen(h)}>Edit</Button>
                                    <Button size='small' color='error' onClick={() => remove(h.id)}>Delete</Button>
                                </CardActions>
                            )}
                        </Card>
                    </Grid>
                ))}
            </Grid>

            <Dialog open={dialogOpen} onClose={handleClose} maxWidth='sm' fullWidth>
                <DialogTitle>{editing ? 'Edit Host' : 'Add Host'}</DialogTitle>
                <DialogContent sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 1 }}>
                    <TextField label='Name' value={form.name}
                               onChange={e => setForm(p => ({ ...p, name: e.target.value }))} />
                    <TextField label='Surname' value={form.surname}
                               onChange={e => setForm(p => ({ ...p, surname: e.target.value }))} />
                    <TextField select label='Country' value={form.countryId}
                               onChange={e => setForm(p => ({ ...p, countryId: Number(e.target.value) }))}>
                        {countries.map(c => (
                            <MenuItem key={c.id} value={c.id}>{c.name}</MenuItem>
                        ))}
                    </TextField>
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

export default HostsPage;