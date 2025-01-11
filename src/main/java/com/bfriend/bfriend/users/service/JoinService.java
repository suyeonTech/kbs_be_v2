package com.bfriend.bfriend.users.service;

import com.bfriend.bfriend.security.JWTUtil;
import com.bfriend.bfriend.users.dto.request.RequestJoinDTO;
import com.bfriend.bfriend.users.dto.response.ResponseJoinDTO;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.utils.constants.JWTConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public ResponseEntity<?> joinProcess(RequestJoinDTO requestJoinDTO) {

        if (userRepository.findByEmail(requestJoinDTO.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ResponseJoinDTO("이미 사용 중인 이메일입니다.", false));
        }

        Users users = buildUsers(requestJoinDTO);
        userRepository.save(users);

        String token = jwtUtil.createJwt(users.getEmail(), users.getRole(), JWTConstants.TOKEN_VALIDITY_MILLISECONDS_1HOUR * 24); // 24시간 유효한 토큰

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Authorization", JWTConstants.TOKEN_PREFIX + token)
                .header("Location", "/") // 리다이렉트 경로 설정
                .body(new ResponseJoinDTO("회원가입이 완료되었습니다.", true));
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        // 모든 유효성 검증 실패 항목을 처리
        for (FieldError error : errors.getFieldErrors()) {
            String key = "valid_" + error.getField();
            String message = error.getDefaultMessage();
            validatorResult.put(key, message);
        }

        return validatorResult;
    }

    private Users buildUsers(RequestJoinDTO requestJoinDTO) {
        return Users.builder()
                .nickname(requestJoinDTO.getNickname())
                .password(passwordEncoder.encode(requestJoinDTO.getPassword()))
                .gender(requestJoinDTO.getGender())
                .age(requestJoinDTO.getAge())
                .email(requestJoinDTO.getEmail())
                .role("ROLE_USER")
                .isReported(false)
                .isStopped(false)
                .build();
    }
}
