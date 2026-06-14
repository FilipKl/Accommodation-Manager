package mk.ukim.finki.emtlab.service.domain.impl;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Review;
import mk.ukim.finki.emtlab.repository.ReviewRepository;
import mk.ukim.finki.emtlab.service.domain.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAllByAccommodationId(Long accommodationId) {
        return reviewRepository.findAllByAccommodationId(accommodationId);
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }
}