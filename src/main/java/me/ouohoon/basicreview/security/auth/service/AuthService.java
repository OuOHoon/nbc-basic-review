package me.ouohoon.basicreview.security.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.security.auth.jwt.JwtUtil;
import me.ouohoon.basicreview.security.auth.request.LoginRequest;
import me.ouohoon.basicreview.user.domain.User;
import me.ouohoon.basicreview.user.repository.UserRepository;
import me.ouohoon.basicreview.user.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public UserResponse login(LoginRequest request, HttpServletResponse response) {
        User user = userRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> new BaseException(ErrorCode.WRONG_NICKNAME_PASSWORD));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorCode.WRONG_NICKNAME_PASSWORD);
        }

        String token = jwtUtil.createToken(user.getNickname());
        jwtUtil.addJwtToCookie(token, response);

        return new UserResponse(user);
    }
}
