package mk.ukim.finki.emtlab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.model.enums.Role;
import mk.ukim.finki.emtlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class DataInitializerForAdminUser {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username:admin}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email:admin@gmail.com}")
    private String adminEmail;

    public DataInitializerForAdminUser(UserRepository userRepository,
                                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User administrator = new User(
                    "Admin",
                    "Admin",
                    adminEmail,
                    adminUsername,
                    passwordEncoder.encode(adminPassword),
                    Role.ROLE_ADMINISTRATOR
            );
            userRepository.save(administrator);
        }
    }
}