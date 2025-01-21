package com.bfriend.bfriend.exception;

/**
 * 해당 값을 가진 데이터가 없을 때 발생하는 예외처리
 */
public class NotFoundException extends BusinessException {
    private String value;

    public NotFoundException(ExceptionCode exceptionCode, String value) {
        super(exceptionCode);
        this.value = value;
    }
}



