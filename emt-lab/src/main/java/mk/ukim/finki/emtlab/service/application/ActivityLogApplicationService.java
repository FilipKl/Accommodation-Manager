package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.model.dto.DisplayActivityLogDto;
import mk.ukim.finki.emtlab.model.dto.PopularAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.PopularHostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityLogApplicationService {
    Page<DisplayActivityLogDto> findAll(Pageable pageable);

    Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable);

    Page<PopularHostDto> findMostPopularHosts(Pageable pageable);
}