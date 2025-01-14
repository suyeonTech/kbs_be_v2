package com.bfriend.bfriend.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 회원가입
    DUPLICATE_EMAIL(400, "DUPLICATE_EMAIL", "이미 사용 중인 이메일입니다."),
    INVALID_INPUT_VALUE(400, "INVALID_INPUT_VALUE", "입력값이 유효하지 않습니다."),

    // 서버 에러
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버에 문제가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;
}
