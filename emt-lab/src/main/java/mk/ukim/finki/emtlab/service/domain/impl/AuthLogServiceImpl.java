package mk.ukim.finki.emtlab.service.domain.impl;

import java.util.List;
import mk.ukim.finki.emtlab.helpers.JwtHelper;
import mk.ukim.finki.emtlab.model.domain.AuthLog;
import mk.ukim.finki.emtlab.repository.AuthLogRepository;
import mk.ukim.finki.emtlab.service.domain.AuthLogService;
import org.springframework.stereotype.Service;

@Service
public class AuthLogServiceImpl implements AuthLogService {
    private final AuthLogRepository authLogRepository;
    private final JwtHelper jwtHelper;

    public AuthLogServiceImpl(AuthLogRepository authLogRepository, JwtHelper jwtHelper) {
        this.authLogRepository = authLogRepository;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public List<AuthLog> findAll() {
        return authLogRepository.findAll();
    }

    @Override
    public void save(String username, String token) {
        authLogRepository.save(new AuthLog(
                username,
                token,
                jwtHelper.extractIssuedAt(token),
                jwtHelper.extractExpiration(token)
        ));
    }
}