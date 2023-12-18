package me.ouohoon.basicreview.security.auth.controller;


import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.dto.BaseResponse;
import me.ouohoon.basicreview.security.auth.request.LoginRequest;
import me.ouohoon.basicreview.security.auth.service.AuthService;
import me.ouohoon.basicreview.user.domain.User;
import me.ouohoon.basicreview.user.response.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<UserResponse>> login(@RequestBody LoginRequest request) {
        UserResponse user = authService.login(request);

        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(BaseResponse.success(user));
    }
}