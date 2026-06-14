package mk.ukim.finki.emtlab.repository;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByAccommodationId(Long accommodationId);
}