package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    @Query("""
        select a.accommodationName, count(a) as rentCount
        from ActivityLog a
        where a.eventType = 'ACCOMMODATION_RENTED'
        group by a.accommodationName
        order by rentCount desc
        """)
    Page<Object[]> findMostPopularAccommodations(Pageable pageable);

    @Query(value = """
        select h.name || ' ' || h.surname as hostName, count(al.id) as rentCount
        from activity_log al
        join accommodations a on a.name = al.accommodation_name
        join hosts h on h.id = a.host_id
        where al.event_type = 'ACCOMMODATION_RENTED'
        group by h.name, h.surname
        order by rentCount desc
        """, nativeQuery = true)
    Page<Object[]> findMostPopularHosts(Pageable pageable);
}