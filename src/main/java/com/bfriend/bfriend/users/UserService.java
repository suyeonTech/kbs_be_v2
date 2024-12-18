package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public String changePassword(ChangePasswordRequest request) {
        if (!isValidPasswordFormat(request.getNewPassword())) {
            throw new IllegalArgumentException();
        }

        String hashedNewPassword = passwordEncoder.encode(request.getNewPassword());

        saveHashedNewPassword(request.getEmail(), hashedNewPassword);

        return "success";
    }

    public boolean isValidPasswordFormat(String newPassword) {
        int passwordMinLength = (int)PasswordCheckConstant.PASSWORD_MIN_LENGTH.getValue();
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
                .orElseThrow(IllegalArgumentException::new);

        Users updatePasswordUsers = users.toBuilder()
                .password(hashedNewPassword)
                .build();

        usersRepository.save(updatePasswordUsers);
    }
}
