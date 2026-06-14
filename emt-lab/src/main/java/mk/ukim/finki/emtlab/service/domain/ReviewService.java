package mk.ukim.finki.emtlab.service.domain;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Review;

public interface ReviewService {
    List<Review> findAllByAccommodationId(Long accommodationId);

    Review create(Review review);
}