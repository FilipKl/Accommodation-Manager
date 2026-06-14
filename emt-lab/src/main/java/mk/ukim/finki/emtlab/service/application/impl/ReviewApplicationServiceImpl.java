package mk.ukim.finki.emtlab.service.application.impl;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.dto.CreateReviewDto;
import mk.ukim.finki.emtlab.model.dto.DisplayReviewDto;
import mk.ukim.finki.emtlab.model.exception.AccommodationNotFoundException;
import mk.ukim.finki.emtlab.service.application.ReviewApplicationService;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {
    private final ReviewService reviewService;
    private final AccommodationService accommodationService;

    public ReviewApplicationServiceImpl(ReviewService reviewService,
                                        AccommodationService accommodationService) {
        this.reviewService = reviewService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<DisplayReviewDto> findAllByAccommodationId(Long accommodationId) {
        accommodationService
                .findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
        return DisplayReviewDto.from(reviewService.findAllByAccommodationId(accommodationId));
    }

    @Override
    public DisplayReviewDto create(Long accommodationId, CreateReviewDto createReviewDto) {
        Accommodation accommodation = accommodationService
                .findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
        return DisplayReviewDto.from(reviewService.create(createReviewDto.toReview(accommodation)));
    }

    @Override
    public Double getAverageGrade(Long accommodationId) {
        accommodationService
                .findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
        return reviewService.findAllByAccommodationId(accommodationId)
                .stream()
                .mapToInt(review -> review.getGrade())
                .average()
                .orElse(0.0);
    }
}