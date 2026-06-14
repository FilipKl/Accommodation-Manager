package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "auth_logs")
public class AuthLog extends BaseEntity {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 512)
    private String token;

    @Column(nullable = false)
    private Date issuedAt;

    @Column(nullable = false)
    private Date expiresAt;

    public AuthLog(String username, String token, Date issuedAt, Date expiresAt) {
        this.username = username;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }
}