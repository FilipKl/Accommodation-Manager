package mk.ukim.finki.emtlab.listener;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.emtlab.events.AccommodationRentedEvent;
import mk.ukim.finki.emtlab.model.domain.ActivityLog;
import mk.ukim.finki.emtlab.repository.ActivityLogRepository;
import mk.ukim.finki.emtlab.repository.AccommodationRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class RentEventListener {
    private final ActivityLogRepository activityLogRepository;
    private final AccommodationRepository accommodationRepository;

    public RentEventListener(ActivityLogRepository activityLogRepository,
                             AccommodationRepository accommodationRepository) {
        this.activityLogRepository = activityLogRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onAccommodationRented(AccommodationRentedEvent event) {
        log.info("[ASYNC - thread: {}] Accommodation '{}' has been rented.",
                Thread.currentThread().getName(), event.accommodationName());

        activityLogRepository.save(new ActivityLog(
                event.accommodationName(),
                LocalDateTime.now(),
                "ACCOMMODATION_RENTED"
        ));

        accommodationRepository.findById(event.accommodationId()).ifPresent(accommodation -> {
            if (accommodation.getNumRooms() == 0) {
                log.info("Accommodation '{}' is now fully occupied!",
                        event.accommodationName());
                activityLogRepository.save(new ActivityLog(
                        event.accommodationName(),
                        LocalDateTime.now(),
                        "ACCOMMODATION_FULLY_OCCUPIED"
                ));
            }
        });
    }
}