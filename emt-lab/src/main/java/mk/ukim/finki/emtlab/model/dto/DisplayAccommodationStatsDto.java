package mk.ukim.finki.emtlab.model.dto;

import java.math.BigDecimal;
import java.util.List;
import mk.ukim.finki.emtlab.model.views.AccommodationStatsView;

public record DisplayAccommodationStatsDto(
        String category,
        Long totalAccommodations,
        Long totalRooms,
        BigDecimal averageRooms
) {
    public static DisplayAccommodationStatsDto from(AccommodationStatsView view) {
        return new DisplayAccommodationStatsDto(
                view.getCategory(),
                view.getTotalAccommodations(),
                view.getTotalRooms(),
                view.getAverageRooms()
        );
    }

    public static List<DisplayAccommodationStatsDto> from(List<AccommodationStatsView> views) {
        return views.stream()
                .map(DisplayAccommodationStatsDto::from)
                .toList();
    }
}