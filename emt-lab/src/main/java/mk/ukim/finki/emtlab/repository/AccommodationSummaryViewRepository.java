package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.views.AccommodationSummaryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationSummaryViewRepository extends JpaRepository<AccommodationSummaryView, Long> {
}