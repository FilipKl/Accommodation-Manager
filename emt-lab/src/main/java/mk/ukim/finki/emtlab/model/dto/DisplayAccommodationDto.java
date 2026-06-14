package mk.ukim.finki.emtlab.model.dto;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.enums.AccommodationCondition;

public record DisplayAccommodationDto(
        Long id,
        String name,
        AccommodationCategory category,
        AccommodationCondition condition,
        Long hostId,
        String hostFullName,
        Integer numRooms,
        Boolean rented
) {
    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getCondition(),
                accommodation.getHost().getId(),
                accommodation.getHost().getName() + " " + accommodation.getHost().getSurname(),
                accommodation.getNumRooms(),
                accommodation.getRented()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream()
                .map(DisplayAccommodationDto::from)
                .toList();
    }
}