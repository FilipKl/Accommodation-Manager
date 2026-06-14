package mk.ukim.finki.emtlab.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import mk.ukim.finki.emtlab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.projection.AccommodationShortProjection;
import mk.ukim.finki.emtlab.service.application.AccommodationApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationApplicationService.findById(id));
    }

    @GetMapping("/{id}/with-host-and-country")
    public ResponseEntity<DisplayAccommodationDto> findWithHostAndCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationApplicationService.findWithHostAndCountryById(id));
    }

    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDto>> findAll() {
        return ResponseEntity.ok(accommodationApplicationService.findAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<DisplayAccommodationDto>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) AccommodationCategory category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) Integer minRooms,
            @RequestParam(required = false) Boolean hasAvailableRooms
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(accommodationApplicationService.findAllWithFilters(
                category, hostId, countryName, minRooms, hasAvailableRooms, pageable));
    }

    @GetMapping("/short")
    public ResponseEntity<Page<AccommodationShortProjection>> findAllShortProjections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(accommodationApplicationService.findAllShortProjections(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> create(
            @RequestBody @Valid CreateAccommodationDto createAccommodationDto
    ) {
        return ResponseEntity.ok(accommodationApplicationService.create(createAccommodationDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayAccommodationDto> update(
            @PathVariable Long id,
            @RequestBody @Valid CreateAccommodationDto createAccommodationDto
    ) {
        return accommodationApplicationService
                .update(id, createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayAccommodationDto> deleteById(@PathVariable Long id) {
        return accommodationApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id) {
        return accommodationApplicationService
                .markAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}