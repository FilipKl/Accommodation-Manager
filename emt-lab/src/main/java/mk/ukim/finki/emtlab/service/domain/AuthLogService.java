package mk.ukim.finki.emtlab.service.domain;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.AuthLog;

public interface AuthLogService {
    List<AuthLog> findAll();

    void save(String username, String token);
}