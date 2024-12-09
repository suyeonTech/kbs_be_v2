package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class MailAuthenticationNumberService {

    private static final String EMAIL_AUTHENTICATION_NUMBER_NAMESPACE = "email_authentication:";

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.email_authentication_number_ttl}")
    private long emailAuthenticationNumberTTL;

    public MailAuthenticationNumberService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String createAuthenticationNumber() {
        SecureRandom secureRandom = new SecureRandom();
        int authenticationNumber = 100000 + secureRandom.nextInt(900000);

        return String.valueOf(authenticationNumber);
    }

    public void saveAuthenticationNumberToRedis(String email, String authenticationNumber) {
        redisTemplate.opsForValue().set(EMAIL_AUTHENTICATION_NUMBER_NAMESPACE + email, authenticationNumber,
                emailAuthenticationNumberTTL, TimeUnit.SECONDS);
    }

    public String checkAuthenticationNumber(CheckAuthenticationNumberRequest request) {
        String authenticationNumber = (String) redisTemplate.opsForValue().get(EMAIL_AUTHENTICATION_NUMBER_NAMESPACE + request.getEmail());

        if (authenticationNumber == null || !authenticationNumber.equals(request.getAuthenticationNumber())) {
            throw new IllegalArgumentException();
        }

        deleteEmailAndAuthenticationNumber(request.getEmail());

        return "success";
    }

    public void deleteEmailAndAuthenticationNumber(String verifiedEmail) {
        redisTemplate.delete(EMAIL_AUTHENTICATION_NUMBER_NAMESPACE+verifiedEmail);
    }
}
