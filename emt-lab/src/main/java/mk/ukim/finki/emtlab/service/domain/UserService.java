package mk.ukim.finki.emtlab.service.domain;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.model.dto.LoginDto;
import mk.ukim.finki.emtlab.model.dto.RegisterDto;

public interface UserService {
    User register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void deleteById(Long id);
}