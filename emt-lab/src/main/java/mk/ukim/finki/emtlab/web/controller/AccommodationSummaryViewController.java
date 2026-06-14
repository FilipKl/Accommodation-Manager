package mk.ukim.finki.emtlab.web.controller;

import java.util.List;
import mk.ukim.finki.emtlab.model.views.AccommodationSummaryView;
import mk.ukim.finki.emtlab.repository.AccommodationSummaryViewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodations/summary")
public class AccommodationSummaryViewController {
    private final AccommodationSummaryViewRepository accommodationSummaryViewRepository;

    public AccommodationSummaryViewController(
            AccommodationSummaryViewRepository accommodationSummaryViewRepository) {
        this.accommodationSummaryViewRepository = accommodationSummaryViewRepository;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationSummaryView>> findAll() {
        return ResponseEntity.ok(accommodationSummaryViewRepository.findAll());
    }
}