package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.views.AccommodationStatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatsViewRepository extends JpaRepository<AccommodationStatsView, String> {
    @Query(value = "REFRESH MATERIALIZED VIEW CONCURRENTLY accommodation_stats_view", nativeQuery = true)
    @Modifying
    void refresh();
}