package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activity_log")
public class ActivityLog extends BaseEntity {
    @Column(nullable = false)
    private String accommodationName;

    @Column(nullable = false)
    private LocalDateTime eventTime;

    @Column(nullable = false)
    private String eventType;

    public ActivityLog(String accommodationName, LocalDateTime eventTime, String eventType) {
        this.accommodationName = accommodationName;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
}