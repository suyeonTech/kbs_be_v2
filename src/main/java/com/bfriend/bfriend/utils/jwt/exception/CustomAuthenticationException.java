package com.bfriend.bfriend.utils.jwt.exception;

import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CustomAuthenticationException extends AuthenticationException {

 private final ErrorCode errorCode;

 public CustomAuthenticationException(ErrorCode errorCode) {
     super(errorCode.getMessage());
     this.errorCode = errorCode;
 }
}
