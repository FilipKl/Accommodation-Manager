package mk.ukim.finki.emtlab.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Review;

public record CreateReviewDto(
        String comment,

        @NotNull(message = "Grade is required.")
        @Min(value = 1, message = "Grade must be at least 1.")
        @Max(value = 5, message = "Grade must be at most 5.")
        Integer grade
) {
    public Review toReview(Accommodation accommodation) {
        return new Review(comment, grade, accommodation);
    }
}