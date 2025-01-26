package com.bfriend.bfriend.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // AUTH
    DUPLICATE_EMAIL(400, "DUPLICATE_EMAIL", "이미 사용 중인 이메일입니다."),
    INVALID_INPUT_VALUE(400, "INVALID_INPUT_VALUE", "입력값이 유효하지 않습니다."),

    INVALID_CREDENTIALS(401, "INVALID_CREDENTIALS", "이메일 또는 비밀번호가 잘못되었습니다."),
    AUTHENTICATION_FAILED(401, "AUTHENTICATION_FAILED", "인증에 실패하였습니다.");

    private final int status;
    private final String code;
    private final String message;
}
