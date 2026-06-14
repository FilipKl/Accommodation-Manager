package mk.ukim.finki.emtlab.web.controller;

import java.util.List;
import mk.ukim.finki.emtlab.model.dto.DisplayAccommodationStatsDto;
import mk.ukim.finki.emtlab.repository.AccommodationStatsViewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodations/stats")
public class AccommodationStatsController {
    private final AccommodationStatsViewRepository accommodationStatsViewRepository;

    public AccommodationStatsController(
            AccommodationStatsViewRepository accommodationStatsViewRepository) {
        this.accommodationStatsViewRepository = accommodationStatsViewRepository;
    }

    @GetMapping
    public ResponseEntity<List<DisplayAccommodationStatsDto>> findAll() {
        return ResponseEntity.ok(
                DisplayAccommodationStatsDto.from(accommodationStatsViewRepository.findAll())
        );
    }
}