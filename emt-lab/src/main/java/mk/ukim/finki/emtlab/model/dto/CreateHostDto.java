package mk.ukim.finki.emtlab.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.domain.Host;

public record CreateHostDto(
        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Surname is required.")
        String surname,

        @NotNull(message = "Country ID is required.")
        Long countryId
) {
    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}