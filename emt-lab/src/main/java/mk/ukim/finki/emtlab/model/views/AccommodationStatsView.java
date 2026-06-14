package mk.ukim.finki.emtlab.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "accommodation_stats_view")
public class AccommodationStatsView {
    @Id
    private String category;

    private Long totalAccommodations;

    private Long totalRooms;

    private BigDecimal averageRooms;
}