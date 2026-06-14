package mk.ukim.finki.emtlab.web.controller;

import mk.ukim.finki.emtlab.model.dto.DisplayActivityLogDto;
import mk.ukim.finki.emtlab.model.dto.PopularAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.PopularHostDto;
import mk.ukim.finki.emtlab.service.application.ActivityLogApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity-log")
public class ActivityLogController {
    private final ActivityLogApplicationService activityLogApplicationService;

    public ActivityLogController(ActivityLogApplicationService activityLogApplicationService) {
        this.activityLogApplicationService = activityLogApplicationService;
    }

    @GetMapping
    public ResponseEntity<Page<DisplayActivityLogDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("eventTime").descending());
        return ResponseEntity.ok(activityLogApplicationService.findAll(pageable));
    }

    @GetMapping("/most-popular-accommodations")
    public ResponseEntity<Page<PopularAccommodationDto>> findMostPopularAccommodations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(activityLogApplicationService.findMostPopularAccommodations(pageable));
    }

    @GetMapping("/most-popular-hosts")
    public ResponseEntity<Page<PopularHostDto>> findMostPopularHosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(activityLogApplicationService.findMostPopularHosts(pageable));
    }
}