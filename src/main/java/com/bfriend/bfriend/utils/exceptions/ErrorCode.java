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
    AUTHENTICATION_FAILED(UNAUTHORIZED, "AUTH_001", "인증에 실패하였습니다."),
    AUTHENTICATION_HEADER_MISSING(UNAUTHORIZED, "AUTH_002", "Authorization 헤더가 없습니다."),
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_003", "토큰이 만료되었습니다."),
    LOGOUT_TOKEN_USED(UNAUTHORIZED, "AUTH_004", "로그아웃된 토큰입니다."),

    //ROOM
    ROOM_NOTFOUND(BAD_REQUEST, "ROOM_001", "존재하지 않는 모임방입니다."),

    // USERS
    DUPLICATE_EMAIL(BAD_REQUEST, "USERS_001", "이미 사용 중인 이메일입니다."),
    INVALID_INPUT_VALUE(BAD_REQUEST, "USERS_002", "입력값이 유효하지 않습니다."),
    INVALID_CREDENTIALS(UNAUTHORIZED, "USERS_003", "이메일 또는 비밀번호가 잘못되었습니다."),

    USERS_EMAILNOTFOUND(BAD_REQUEST, "USERS_004", "존재하지 않는 이메일입니다."),
    USERS_AUTHENTICIATIONNUMBERNOTFOUND(BAD_REQUEST, "USERS_005", "이메일 인증 번호가 맞지 않습니다."),
    USERS_UIDNOTFOUND(BAD_REQUEST, "USERS_006", "존재하지 않는 사용자 ID입니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
