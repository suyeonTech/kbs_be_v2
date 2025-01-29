package com.bfriend.bfriend.users;

import com.bfriend.bfriend.exception.ExceptionCode;
import com.bfriend.bfriend.exception.NotFoundException;
import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.utils.constants.MailAuthenticationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class MailAuthenticationNumberService {
    String emailAuthenticationNumberNamespace = (String) MailAuthenticationConstants.EMAIL_AUTHENTICATION_NUMBER_NAMESPACE.getValue();

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.email_authentication_number_ttl}")
    private long emailAuthenticationNumberTTL;

    public String create() {
        int minAuthenticationNumber = (int) MailAuthenticationConstants.MIN_AUTHENTICATION_NUMBER.getValue();
        int rangeRandomAuthenticationNumber = (int) MailAuthenticationConstants.RANGE_RANDOM_AUTHENTICATION_NUMBER.getValue();

        SecureRandom secureRandom = new SecureRandom();
        int authenticationNumber = minAuthenticationNumber + secureRandom.nextInt(rangeRandomAuthenticationNumber);

        return String.valueOf(authenticationNumber);
    }

    public void saveToRedis(String email, String authenticationNumber) {
        redisTemplate.opsForValue().set(emailAuthenticationNumberNamespace + email, authenticationNumber,
                emailAuthenticationNumberTTL, TimeUnit.SECONDS);
    }

    public ResponseEntity<String> checkAuthenticationNumber(CheckAuthenticationNumberRequest request) {
        String authenticationNumber = (String) redisTemplate.opsForValue().get(emailAuthenticationNumberNamespace + request.getEmail());

        if (authenticationNumber == null || !authenticationNumber.equals(request.getAuthenticationNumber())) {
            throw new NotFoundException(ExceptionCode.USERS_AUTHENTICIATIONNUMBERNOTFOUND, request.getAuthenticationNumber());
        }

        deleteToRedis(request.getEmail());

        return ResponseEntity.ok("이메일 인증 번호 인증 성공");
    }

    public void deleteToRedis(String verifiedEmail) {
        redisTemplate.delete(emailAuthenticationNumberNamespace + verifiedEmail);
    }
}
