package mk.ukim.finki.emtlab.model.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank String email,
        @NotBlank String username,
        @NotBlank String password
) {}