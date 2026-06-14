package mk.ukim.finki.emtlab.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.enums.AccommodationCondition;

public record CreateAccommodationDto(
        @NotBlank(message = "Name is required.")
        String name,

        @NotNull(message = "Category is required.")
        AccommodationCategory category,

        @NotNull(message = "Condition is required.")
        AccommodationCondition condition,

        @NotNull(message = "Host ID is required.")
        Long hostId,

        @NotNull(message = "Number of rooms is required.")
        @Positive(message = "Number of rooms must be positive.")
        Integer numRooms
) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, condition, host, numRooms);
    }
}