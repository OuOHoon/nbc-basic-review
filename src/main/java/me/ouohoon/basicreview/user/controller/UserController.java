package me.ouohoon.basicreview.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.dto.BaseResponse;
import me.ouohoon.basicreview.user.request.SignUpAuthRequest;
import me.ouohoon.basicreview.user.request.SignUpRequest;
import me.ouohoon.basicreview.user.response.UserResponse;
import me.ouohoon.basicreview.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{nickname}")
    public ResponseEntity<BaseResponse<UserResponse>> getUser(@PathVariable String nickname) {
        UserResponse user = userService.getUser(nickname);
        return ResponseEntity.ok(BaseResponse.success(user));
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<UserResponse>> signUp(@Valid @RequestBody SignUpRequest request) {
        UserResponse user = userService.signUp(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(user));
    }

    @PostMapping("/signup/auth")
    public ResponseEntity<BaseResponse<String>> sendAuthEmail(@Valid @RequestBody SignUpAuthRequest request) {
        userService.sendAuthEmail(request.getEmail());
        return ResponseEntity.ok(BaseResponse.success("인증메일 발송"));
    }
}
