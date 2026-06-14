package mk.ukim.finki.emtlab.service.domain.impl;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.emtlab.helpers.JwtHelper;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.model.dto.LoginDto;
import mk.ukim.finki.emtlab.model.dto.RegisterDto;
import mk.ukim.finki.emtlab.model.enums.Role;
import mk.ukim.finki.emtlab.repository.UserRepository;
import mk.ukim.finki.emtlab.service.domain.AuthLogService;
import mk.ukim.finki.emtlab.service.domain.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private final AuthLogService authLogService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtHelper jwtHelper, AuthLogService authLogService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
        this.authLogService = authLogService;
    }

    @Override
    public User register(RegisterDto dto) {
        User user = new User(dto.name(), dto.surname(), dto.email());
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }


    @Override
    public String login(LoginDto dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }
        String token = jwtHelper.generateToken(user);
        authLogService.save(user.getUsername(), token);
        return token;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}