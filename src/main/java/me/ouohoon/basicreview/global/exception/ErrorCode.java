package me.ouohoon.basicreview.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 유저
    NOT_EXIST_NICKNAME("존재하지 않는 닉네임입니다."),
    WRONG_NICKNAME_PASSWORD("닉네임 또는 패스워드를 확인해주세요."),

    // 이메일
    FAIL_TO_SEND_EMAIL("이메일 전송에 실패했습니다."),
    FAIL_TO_AUTH_EMAIL("이메일 인증에 실패했습니다."),
    ALREADY_SIGNUP_EMAIL("이미 가입한 이메일입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
