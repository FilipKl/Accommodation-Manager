package mk.ukim.finki.emtlab.model.dto;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Review;

public record DisplayReviewDto(
        Long id,
        String comment,
        Integer grade,
        Long accommodationId
) {
    public static DisplayReviewDto from(Review review) {
        return new DisplayReviewDto(
                review.getId(),
                review.getComment(),
                review.getGrade(),
                review.getAccommodation().getId()
        );
    }

    public static List<DisplayReviewDto> from(List<Review> reviews) {
        return reviews.stream()
                .map(DisplayReviewDto::from)
                .toList();
    }
}