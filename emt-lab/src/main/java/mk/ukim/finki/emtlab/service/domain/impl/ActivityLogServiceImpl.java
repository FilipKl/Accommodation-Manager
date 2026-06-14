package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.ActivityLog;
import mk.ukim.finki.emtlab.model.dto.PopularAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.PopularHostDto;
import mk.ukim.finki.emtlab.repository.ActivityLogRepository;
import mk.ukim.finki.emtlab.service.domain.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;

    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public Page<ActivityLog> findAll(Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }

    @Override
    public Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable) {
        return activityLogRepository
                .findMostPopularAccommodations(pageable)
                .map(PopularAccommodationDto::from);
    }

    @Override
    public Page<PopularHostDto> findMostPopularHosts(Pageable pageable) {
        return activityLogRepository
                .findMostPopularHosts(pageable)
                .map(PopularHostDto::from);
    }
}