package mk.ukim.finki.emtlab.service.application;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.dto.CreateHostDto;
import mk.ukim.finki.emtlab.model.dto.DisplayHostDto;

public interface HostApplicationService {
    Optional<DisplayHostDto> findById(Long id);

    List<DisplayHostDto> findAll();

    DisplayHostDto create(CreateHostDto createHostDto);

    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    Optional<DisplayHostDto> deleteById(Long id);
}
