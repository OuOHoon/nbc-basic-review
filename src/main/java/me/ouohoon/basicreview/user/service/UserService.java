package me.ouohoon.basicreview.user.service;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.email.EmailService;
import me.ouohoon.basicreview.email.dto.Email;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.user.domain.User;
import me.ouohoon.basicreview.user.request.SignUpRequest;
import me.ouohoon.basicreview.user.response.UserResponse;
import me.ouohoon.basicreview.user.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final RedisTemplate<String, String> redisTemplate;

    public UserResponse getUser(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_NICKNAME));

        return new UserResponse(user);
    }

    @Transactional
    public UserResponse signUp(SignUpRequest request) {
        // 이미 가입한 이메일 차단
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException(ErrorCode.ALREADY_SIGNUP_EMAIL);
        }

        // 인증 코드 없거나 다르면 실패
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String authCode = operations.get(request.getEmail());
        if (authCode == null || !authCode.equals(request.getAuthCode())) {
            throw new BaseException(ErrorCode.FAIL_TO_AUTH_EMAIL);
        }

        User user = new User(request, passwordEncoder);
        User save = userRepository.save(user);
        return new UserResponse(save);
    }

    @Transactional
    public void sendAuthEmail(String email) {
        // 이미 가입한 이메일 차단
        if (userRepository.existsByEmail(email)) {
            throw new BaseException(ErrorCode.ALREADY_SIGNUP_EMAIL);
        }

        Email authEmail = createAuthEmail(email);
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(email, authEmail.getContent(), 5, TimeUnit.MINUTES);

        emailService.send(authEmail);
    }

    private Email createAuthEmail(String email) {
        return new Email(
                email,
                "basic-review-project 회원가입 인증 메일입니다.",
                createAuthCode()
        );
    }

    private String createAuthCode() {
        Random random = new Random();
        return Integer.toString(random.nextInt(100000, 999999));
    }
}
