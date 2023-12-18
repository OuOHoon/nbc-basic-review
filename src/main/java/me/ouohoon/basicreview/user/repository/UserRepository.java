package me.ouohoon.basicreview.user.repository;

import me.ouohoon.basicreview.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);

    boolean existsByEmail(String email);
}
