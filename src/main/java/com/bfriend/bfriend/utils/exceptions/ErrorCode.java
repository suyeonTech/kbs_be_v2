package com.bfriend.bfriend.utils.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    // AUTH
    DUPLICATE_EMAIL(BAD_REQUEST, "DUPLICATE_EMAIL", "이미 사용 중인 이메일입니다."),
    INVALID_INPUT_VALUE(BAD_REQUEST, "INVALID_INPUT_VALUE", "입력값이 유효하지 않습니다."),

    INVALID_CREDENTIALS(UNAUTHORIZED, "INVALID_CREDENTIALS", "이메일 또는 비밀번호가 잘못되었습니다."),
    AUTHENTICATION_FAILED(UNAUTHORIZED, "AUTHENTICATION_FAILED", "인증에 실패하였습니다."),

    //ROOM
    ROOM_NOTFOUND(BAD_REQUEST, "ROOM_001", "존재하지 않는 모임방입니다."),

    // USERS
    USERS_EMAILNOTFOUND(BAD_REQUEST, "USERS_001", "존재하지 않는 이메일입니다."),
    USERS_AUTHENTICIATIONNUMBERNOTFOUND(BAD_REQUEST, "USERS_002", "이메일 인증 번호가 맞지 않습니다."),
    USERS_UIDNOTFOUND(BAD_REQUEST, "USERS_003", "존재하지 않는 사용자 ID입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
