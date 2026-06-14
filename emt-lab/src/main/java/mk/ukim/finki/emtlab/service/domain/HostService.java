package mk.ukim.finki.emtlab.service.domain;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.Host;

public interface HostService {
    Optional<Host> findById(Long id);

    List<Host> findAll();

    Host create(Host host);

    Optional<Host> update(Long id, Host host);

    Optional<Host> deleteById(Long id);
}