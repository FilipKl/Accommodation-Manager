package mk.ukim.finki.emtlab.service.domain.impl;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.repository.HostRepository;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Host create(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository
                .findById(id)
                .map(existing -> {
                    existing.setName(host.getName());
                    existing.setSurname(host.getSurname());
                    existing.setCountry(host.getCountry());
                    return hostRepository.save(existing);
                });
    }

    @Override
    public Optional<Host> deleteById(Long id) {
        Optional<Host> host = hostRepository.findById(id);
        host.ifPresent(hostRepository::delete);
        return host;
    }
}