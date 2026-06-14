package mk.ukim.finki.emtlab.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import mk.ukim.finki.emtlab.model.dto.CreateHostDto;
import mk.ukim.finki.emtlab.model.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayHostDto>> findAll() {
        return ResponseEntity.ok(hostApplicationService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> create(@RequestBody @Valid CreateHostDto createHostDto) {
        return ResponseEntity.ok(hostApplicationService.create(createHostDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayHostDto> update(
            @PathVariable Long id,
            @RequestBody @Valid CreateHostDto createHostDto
    ) {
        return hostApplicationService
                .update(id, createHostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayHostDto> deleteById(@PathVariable Long id) {
        return hostApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}