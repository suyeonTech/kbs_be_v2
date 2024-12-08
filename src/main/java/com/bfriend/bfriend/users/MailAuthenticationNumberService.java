package com.bfriend.bfriend.users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class MailAuthenticationNumberService {

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
        redisTemplate.opsForValue().set("email_authentication:"+email, authenticationNumber,
                emailAuthenticationNumberTTL, TimeUnit.SECONDS);
    }
}
