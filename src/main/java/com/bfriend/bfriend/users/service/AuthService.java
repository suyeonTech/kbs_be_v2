package com.bfriend.bfriend.users.service;

import com.bfriend.bfriend.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final RedisTemplate<String, String> stringRedisTemplate;
    private final JWTUtil jwtUtil;

    public void logout(String accessToken) {
        String token = accessToken.replace("Bearer ", "");

        long expiration = getRemainingTime(token);

        stringRedisTemplate.opsForValue().set(
                token,
                "logout",
                expiration,
                TimeUnit.MILLISECONDS
        );
    }

    private long getRemainingTime(String token) {
        Date expiry = jwtUtil.getExpiration(token);
        return expiry.getTime() - System.currentTimeMillis();
    }

}
