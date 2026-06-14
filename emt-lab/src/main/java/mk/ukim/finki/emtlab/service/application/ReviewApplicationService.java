package mk.ukim.finki.emtlab.service.application;

import java.util.List;
import mk.ukim.finki.emtlab.model.dto.CreateReviewDto;
import mk.ukim.finki.emtlab.model.dto.DisplayReviewDto;

public interface ReviewApplicationService {
    List<DisplayReviewDto> findAllByAccommodationId(Long accommodationId);

    DisplayReviewDto create(Long accommodationId, CreateReviewDto createReviewDto);

    Double getAverageGrade(Long accommodationId);
}