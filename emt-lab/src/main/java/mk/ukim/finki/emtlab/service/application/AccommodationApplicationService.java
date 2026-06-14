package mk.ukim.finki.emtlab.service.application;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccommodationApplicationService {
    DisplayAccommodationDto findById(Long id);

    DisplayAccommodationDto findWithHostAndCountryById(Long id);

    List<DisplayAccommodationDto> findAll();

    Page<DisplayAccommodationDto> findAllWithFilters(AccommodationCategory category, Long hostId,
                                                     String countryName, Integer minRooms,
                                                     Boolean hasAvailableRooms, Pageable pageable);

    Page<AccommodationShortProjection> findAllShortProjections(Pageable pageable);

    DisplayAccommodationDto create(CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> deleteById(Long id);

    Optional<DisplayAccommodationDto> markAsRented(Long id);
}