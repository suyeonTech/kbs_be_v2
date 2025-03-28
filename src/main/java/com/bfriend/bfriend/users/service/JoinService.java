package com.bfriend.bfriend.users.service;

import com.bfriend.bfriend.security.JWTUtil;
import com.bfriend.bfriend.users.dto.request.JoinRequest;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.utils.constants.JWTConstants;
import com.bfriend.bfriend.utils.exceptions.BusinessException;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import com.bfriend.bfriend.utils.exceptions.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;


    public ResponseEntity<?> joinProcess(JoinRequest joinRequest) {

        if (userRepository.findByEmail(joinRequest.getEmail()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        Users users = buildUsers(joinRequest);
        userRepository.save(users);

        String token = jwtUtil.createJwt(users.getEmail(), users.getRole(), JWTConstants.TOKEN_VALIDITY_MILLISECONDS_1HOUR * 24); // 24시간 유효한 토큰

        record SignupResponse(int status, String message, String nickname) {}
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Authorization", JWTConstants.TOKEN_PREFIX + token)
                .body(new SignupResponse(HttpStatus.CREATED.value(), "회원가입이 완료되었습니다.", joinRequest.getNickname()));

    }

    private Users buildUsers(JoinRequest joinRequest) {
        return Users.builder()
                .nickname(joinRequest.getNickname())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .gender(joinRequest.getGender())
                .age(joinRequest.getAge())
                .email(joinRequest.getEmail())
                .role("ROLE_USER")
                .isReported(false)
                .isStopped(false)
                .build();
    }
}
