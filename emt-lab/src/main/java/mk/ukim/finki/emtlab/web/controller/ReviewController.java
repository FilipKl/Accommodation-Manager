package mk.ukim.finki.emtlab.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import mk.ukim.finki.emtlab.model.dto.CreateReviewDto;
import mk.ukim.finki.emtlab.model.dto.DisplayReviewDto;
import mk.ukim.finki.emtlab.service.application.ReviewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodations/{accommodationId}/reviews")
public class ReviewController {
    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayReviewDto>> findAllByAccommodationId(
            @PathVariable Long accommodationId
    ) {
        return ResponseEntity.ok(reviewApplicationService.findAllByAccommodationId(accommodationId));
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getAverageGrade(@PathVariable Long accommodationId) {
        return ResponseEntity.ok(reviewApplicationService.getAverageGrade(accommodationId));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayReviewDto> create(
            @PathVariable Long accommodationId,
            @RequestBody @Valid CreateReviewDto createReviewDto
    ) {
        return ResponseEntity.ok(reviewApplicationService.create(accommodationId, createReviewDto));
    }
}