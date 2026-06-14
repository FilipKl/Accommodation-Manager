package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.enums.AccommodationCategory;
import mk.ukim.finki.emtlab.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @EntityGraph(value = "accommodation-with-host-and-country",
            type = EntityGraph.EntityGraphType.FETCH)
    Optional<Accommodation> findWithHostAndCountryById(Long id);

    @Query("""
        select a from Accommodation a
        join a.host h
        join h.country c
        where (:category is null or a.category = :category)
        and (:hostId is null or h.id = :hostId)
        and (:countryName is null or c.name = :countryName)
        and (:minRooms is null or a.numRooms >= :minRooms)
        and (:hasAvailableRooms is null or
            (:hasAvailableRooms = true and a.rented = false) or
            (:hasAvailableRooms = false and a.rented = true))
        """)
    Page<Accommodation> findAllWithFilters(
            @Param("category") AccommodationCategory category,
            @Param("hostId") Long hostId,
            @Param("countryName") String countryName,
            @Param("minRooms") Integer minRooms,
            @Param("hasAvailableRooms") Boolean hasAvailableRooms,
            Pageable pageable
    );

    @Query("""
        select a.id as id, a.name as name, a.category as category, a.numRooms as numRooms
        from Accommodation a
        """)
    Page<AccommodationShortProjection> findAllShortProjections(Pageable pageable);
}