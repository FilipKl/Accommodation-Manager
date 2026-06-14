package mk.ukim.finki.emtlab.web.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.security.SignatureException;
import mk.ukim.finki.emtlab.web.dto.JwtExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionHandler {
    private ResponseEntity<JwtExceptionResponseDto> buildResponse(HttpStatus status, String message, String path) {
        return new ResponseEntity<>(
                new JwtExceptionResponseDto(status.value(), status.getReasonPhrase(), message, path),
                status
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<JwtExceptionResponseDto> handleExpired(ExpiredJwtException ex, HttpServletRequest req) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "The token has expired.", req.getRequestURI());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<JwtExceptionResponseDto> handleSignature(SignatureException ex, HttpServletRequest req) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "The token's signature is invalid.", req.getRequestURI());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<JwtExceptionResponseDto> handleJwt(JwtException ex, HttpServletRequest req) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "The token is invalid.", req.getRequestURI());
    }
}