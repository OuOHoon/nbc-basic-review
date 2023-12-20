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

    // 게시글
    NOT_EXIST_POST("존재하지 않는 게시글입니다."),

    // 댓글
    NOT_EXIST_COMMENT("존재하지 않는 댓글입니다."),

    // 좋아요
    ALREADY_LIKE("이미 좋아요를 눌렀습니다."),
    NOT_EXIST_LIKE("좋아요한 기록이 없습니다."),

    // 파일 업로드
    FAIL_TO_IMAGE_UPLOAD("파일 업로드에 실패했습니다."),

    // 정렬
    INVALID_SORT_DIRECTION("정렬 기준이 올바르지 않습니다."),

    // 권한
    PERMISSION_DENIED("권한이 없습니다."),

    // 인증
    AUTHORIZATION_ERROR("인증 정보가 올바르지 않습니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
