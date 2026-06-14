package mk.ukim.finki.emtlab.web.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(countryRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> create(@RequestBody Country country) {
        return ResponseEntity.ok(countryRepository.save(country));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody Country country) {
        return countryRepository.findById(id).map(existing -> {
            existing.setName(country.getName());
            existing.setContinent(country.getContinent());
            return ResponseEntity.ok(countryRepository.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Country> delete(@PathVariable Long id) {
        return countryRepository.findById(id).map(existing -> {
            countryRepository.delete(existing);
            return ResponseEntity.ok(existing);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}