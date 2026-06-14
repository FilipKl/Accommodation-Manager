package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.model.dto.DisplayActivityLogDto;
import mk.ukim.finki.emtlab.model.dto.PopularAccommodationDto;
import mk.ukim.finki.emtlab.model.dto.PopularHostDto;
import mk.ukim.finki.emtlab.service.application.ActivityLogApplicationService;
import mk.ukim.finki.emtlab.service.domain.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogApplicationServiceImpl implements ActivityLogApplicationService {
    private final ActivityLogService activityLogService;

    public ActivityLogApplicationServiceImpl(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @Override
    public Page<DisplayActivityLogDto> findAll(Pageable pageable) {
        return activityLogService.findAll(pageable).map(DisplayActivityLogDto::from);
    }

    @Override
    public Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable) {
        return activityLogService.findMostPopularAccommodations(pageable);
    }

    @Override
    public Page<PopularHostDto> findMostPopularHosts(Pageable pageable) {
        return activityLogService.findMostPopularHosts(pageable);
    }
}