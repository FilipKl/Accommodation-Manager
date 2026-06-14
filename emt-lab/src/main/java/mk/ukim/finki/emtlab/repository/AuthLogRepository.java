package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.AuthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {
}