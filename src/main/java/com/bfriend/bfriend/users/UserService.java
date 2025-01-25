package com.bfriend.bfriend.users;

import com.bfriend.bfriend.exception.ExceptionCode;
import com.bfriend.bfriend.exception.NotFoundException;
import com.bfriend.bfriend.users.dto.request.ChangePasswordRequest;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.utils.constants.PasswordCheckConstant;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final MailService mailService;

    public ResponseEntity<String> changePassword(ChangePasswordRequest request) {
        if (!isValidPasswordFormat(request.getNewPassword())) {
            throw new IllegalArgumentException();
        }

        String hashedNewPassword = passwordEncoder.encode(request.getNewPassword());

        saveHashedNewPassword(request.getEmail(), hashedNewPassword);

        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    public boolean isValidPasswordFormat(String newPassword) {
        int passwordMinLength = (int) PasswordCheckConstant.PASSWORD_MIN_LENGTH.getValue();
        int passwordMaxLength = (int) PasswordCheckConstant.PASSWORD_MAX_LENGTH.getValue();
        String passwordRegex = (String) PasswordCheckConstant.PASSWORD_REGEX.getValue();

        // 공백이 포함된 비밀번호 검사
        String temp = StringUtils.trimAllWhitespace(newPassword);
        if (newPassword.length() != temp.length()) {
            return false;
        }

        if (newPassword.length() < passwordMinLength || newPassword.length() > passwordMaxLength) {
            return false;
        }

        if (!newPassword.matches(passwordRegex)) {
            return false;
        }

        return true;
    }

    public void saveHashedNewPassword(String email, String hashedNewPassword) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.USERS_EMAILNOTFOUND, email));

        Users updatePasswordUsers = users.toBuilder()
                .password(hashedNewPassword)
                .build();

        usersRepository.save(updatePasswordUsers);
    }
      
    public CompletableFuture<ResponseEntity<String>> checkEmail(CheckEmailRequest request) {
        Users users = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.USERS_EMAILNOTFOUND, request.getEmail()));

        return mailService.sendMail(users.getEmail());
    }

    public ResponseEntity<String> checkAuthenticationNumber(CheckAuthenticationNumberRequest request) {
        return mailService.checkAuthenticationNumber(request);
    }
}
