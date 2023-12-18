package me.ouohoon.basicreview.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.user.request.validator.ValidSignUpRequest;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ValidSignUpRequest
public class SignUpRequest {

    private String email;

    private String authCode;

    private String nickname;

    private String password;

    private String passwordConfirm;
}
