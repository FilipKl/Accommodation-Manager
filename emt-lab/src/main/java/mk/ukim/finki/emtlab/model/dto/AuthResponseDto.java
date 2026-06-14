package mk.ukim.finki.emtlab.model.dto;

public record AuthResponseDto(
        String token,
        String username,
        String role
) {}