package mk.ukim.finki.emtlab.service.domain;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccommodationService {
    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> findWithHostAndCountryById(Long id);

    List<Accommodation> findAll();

    Page<Accommodation> findAllWithFilters(AccommodationCategory category, Long hostId,
                                           String countryName, Integer minRooms,
                                           Boolean hasAvailableRooms, Pageable pageable);

    Page<AccommodationShortProjection> findAllShortProjections(Pageable pageable);

    Accommodation create(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);
}