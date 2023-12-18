package me.ouohoon.basicreview.global.dto;

import lombok.Getter;

@Getter
public enum ValidationCode {

    SIGNUP_NICKNAME("nickname", "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다."),
    SIGNUP_PASSWORD("password", "비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함되면 안됩니다."),
    SIGNUP_PASSWORD_CONFIRM("passwordConfirm", "비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다.")
    ;

    private final String field;
    private final String message;

    ValidationCode(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
