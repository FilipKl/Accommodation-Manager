package mk.ukim.finki.emtlab.service.application.impl;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.dto.CreateHostDto;
import mk.ukim.finki.emtlab.model.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.model.exception.CountryNotFoundException;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService
                .findById(id)
                .map(DisplayHostDto::from);
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public DisplayHostDto create(CreateHostDto createHostDto) {
        Country country = countryService
                .findById(createHostDto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(createHostDto.countryId()));
        return DisplayHostDto.from(hostService.create(createHostDto.toHost(country)));
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Country country = countryService
                .findById(createHostDto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(createHostDto.countryId()));
        return hostService
                .update(id, createHostDto.toHost(country))
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> deleteById(Long id) {
        return hostService
                .deleteById(id)
                .map(DisplayHostDto::from);
    }
}