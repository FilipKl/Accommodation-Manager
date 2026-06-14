package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews")
public class Review extends BaseAuditableEntity {
    private String comment;

    @Column(nullable = false)
    private Integer grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    public Review(String comment, Integer grade, Accommodation accommodation) {
        this.comment = comment;
        this.grade = grade;
        this.accommodation = accommodation;
    }
}