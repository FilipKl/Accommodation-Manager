package mk.ukim.finki.emtlab.model.dto;

public record PopularHostDto(
        String hostName,
        Long rentCount
) {
    public static PopularHostDto from(Object[] row) {
        return new PopularHostDto(
                (String) row[0],
                ((Number) row[1]).longValue()
        );
    }
}