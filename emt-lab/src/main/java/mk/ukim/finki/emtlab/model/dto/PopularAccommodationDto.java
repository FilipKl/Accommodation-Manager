package mk.ukim.finki.emtlab.model.dto;

public record PopularAccommodationDto(
        String accommodationName,
        Long rentCount
) {
    public static PopularAccommodationDto from(Object[] row) {
        return new PopularAccommodationDto(
                (String) row[0],
                ((Number) row[1]).longValue()
        );
    }
}