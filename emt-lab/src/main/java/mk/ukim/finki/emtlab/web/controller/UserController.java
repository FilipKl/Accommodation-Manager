package mk.ukim.finki.emtlab.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import mk.ukim.finki.emtlab.model.domain.AuthLog;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.model.dto.AuthResponseDto;
import mk.ukim.finki.emtlab.model.dto.LoginDto;
import mk.ukim.finki.emtlab.model.dto.RegisterDto;
import mk.ukim.finki.emtlab.service.domain.AuthLogService;
import mk.ukim.finki.emtlab.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthLogService authLogService;

    public UserController(UserService userService, AuthLogService authLogService) {
        this.userService = userService;
        this.authLogService = authLogService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid RegisterDto registerDto) {
        User user = userService.register(registerDto);
        String token = userService.login(new LoginDto(registerDto.username(), registerDto.password()));
        return ResponseEntity.ok(new AuthResponseDto(token, user.getUsername(), user.getRole().name()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginDto loginDto) {
        String token = userService.login(loginDto);
        User user = userService.findByUsername(loginDto.username()).orElseThrow();
        return ResponseEntity.ok(new AuthResponseDto(token, user.getUsername(), user.getRole().name()));
    }

    @GetMapping("/auth-logs")
    public ResponseEntity<List<AuthLog>> getAuthLogs() {
        return ResponseEntity.ok(authLogService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}