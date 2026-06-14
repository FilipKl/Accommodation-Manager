package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.enums.AccommodationCondition;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accommodations")
@NamedEntityGraph(
        name = "accommodation-with-host-and-country",
        attributeNodes = {
                @NamedAttributeNode(value = "host", subgraph = "host-with-country")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "host-with-country",
                        attributeNodes = {
                                @NamedAttributeNode("country")
                        }
                )
        }
)
public class Accommodation extends BaseAuditableEntity {
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccommodationCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccommodationCondition condition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Column(nullable = false)
    private Integer numRooms;

    @Column(nullable = false)
    private Boolean rented = false;

    @OneToMany(mappedBy = "accommodation", fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    public Accommodation(String name, AccommodationCategory category, AccommodationCondition condition,
                         Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.host = host;
        this.numRooms = numRooms;
        this.rented = false;
    }
}