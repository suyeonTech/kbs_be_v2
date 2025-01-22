package com.bfriend.bfriend.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    //ROOM
    ROOM_NOTFOUND(BAD_REQUEST, "ROOM_001", "존재하지 않는 모임방입니다."),

    // USERS
    USERS_EMAILNOTFOUND(BAD_REQUEST, "USERS_001", "존재하지 않는 이메일입니다."),
    USERS_WRONGAUTHENTICIATIONNUMBER(BAD_REQUEST, "USERS_002", "이메일 인증 번호가 맞지 않습니다.");

    private final HttpStatus status;

    private final String code;
    private final String message;

}