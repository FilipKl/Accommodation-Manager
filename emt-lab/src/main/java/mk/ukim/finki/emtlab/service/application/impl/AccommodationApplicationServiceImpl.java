package mk.ukim.finki.emtlab.service.application.impl;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.exception.AccommodationNotFoundException;
import mk.ukim.finki.emtlab.model.exception.HostNotFoundException;
import mk.ukim.finki.emtlab.model.projection.AccommodationShortProjection;
import mk.ukim.finki.emtlab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService,
                                               HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public DisplayAccommodationDto findById(Long id) {
        return accommodationService
                .findById(id)
                .map(DisplayAccommodationDto::from)
                .orElseThrow(() -> new AccommodationNotFoundException(id));
    }

    @Override
    public DisplayAccommodationDto findWithHostAndCountryById(Long id) {
        return accommodationService
                .findWithHostAndCountryById(id)
                .map(DisplayAccommodationDto::from)
                .orElseThrow(() -> new AccommodationNotFoundException(id));
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Page<DisplayAccommodationDto> findAllWithFilters(AccommodationCategory category,
                                                            Long hostId, String countryName,
                                                            Integer minRooms,
                                                            Boolean hasAvailableRooms,
                                                            Pageable pageable) {
        return accommodationService
                .findAllWithFilters(category, hostId, countryName, minRooms, hasAvailableRooms, pageable)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Page<AccommodationShortProjection> findAllShortProjections(Pageable pageable) {
        return accommodationService.findAllShortProjections(pageable);
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto dto) {
        Host host = hostService
                .findById(dto.hostId())
                .orElseThrow(() -> new HostNotFoundException(dto.hostId()));
        return DisplayAccommodationDto.from(
                accommodationService.create(dto.toAccommodation(host))
        );
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto dto) {
        Host host = hostService
                .findById(dto.hostId())
                .orElseThrow(() -> new HostNotFoundException(dto.hostId()));
        return accommodationService
                .update(id, dto.toAccommodation(host))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> deleteById(Long id) {
        return accommodationService
                .deleteById(id)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService
                .markAsRented(id)
                .map(DisplayAccommodationDto::from);
    }
}