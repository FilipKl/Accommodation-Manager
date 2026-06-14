package mk.ukim.finki.emtlab.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import mk.ukim.finki.emtlab.model.domain.ActivityLog;

public record DisplayActivityLogDto(
        Long id,
        String accommodationName,
        LocalDateTime eventTime,
        String eventType
) {
    public static DisplayActivityLogDto from(ActivityLog activityLog) {
        return new DisplayActivityLogDto(
                activityLog.getId(),
                activityLog.getAccommodationName(),
                activityLog.getEventTime(),
                activityLog.getEventType()
        );
    }

    public static List<DisplayActivityLogDto> from(List<ActivityLog> activityLogs) {
        return activityLogs.stream()
                .map(DisplayActivityLogDto::from)
                .toList();
    }
}