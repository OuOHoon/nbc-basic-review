package me.ouohoon.basicreview.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.user.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
    private String email;

    public User(SignUpRequest request, PasswordEncoder encoder) {
        this.nickname = request.getNickname();
        this.password = encoder.encode(request.getPassword());
        this.email = request.getEmail();
    }
}
