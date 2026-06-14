package mk.ukim.finki.emtlab.service.domain;

import mk.ukim.finki.emtlab.model.domain.ActivityLog;
import mk.ukim.finki.emtlab.model.dto.PopularAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.PopularHostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityLogService {
    Page<ActivityLog> findAll(Pageable pageable);

    Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable);

    Page<PopularHostDto> findMostPopularHosts(Pageable pageable);
}