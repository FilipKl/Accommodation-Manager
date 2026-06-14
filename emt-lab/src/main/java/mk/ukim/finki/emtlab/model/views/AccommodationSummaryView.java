package mk.ukim.finki.emtlab.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "accommodation_summary_view")
public class AccommodationSummaryView {
    @Id
    private Long id;

    private String accommodationName;

    private String category;

    private Integer numRooms;

    private Boolean rented;

    private String hostFullName;

    private String countryName;
}