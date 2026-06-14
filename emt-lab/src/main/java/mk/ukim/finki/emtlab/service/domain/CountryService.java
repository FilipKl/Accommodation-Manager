package mk.ukim.finki.emtlab.service.domain;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.Country;

public interface CountryService {
    Optional<Country> findById(Long id);

    List<Country> findAll();
}