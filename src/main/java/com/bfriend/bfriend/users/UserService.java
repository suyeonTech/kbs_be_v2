package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.ChangePasswordRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 20;
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*+=()_-])(?=.*[0-9]).+$";

    public void changePassword(ChangePasswordRequest request) {
        if (!isValidPasswordFormat(request.getNewPassword())) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isValidPasswordFormat(String newPassword) {
        // 공백이 포함된 비밀번호 검사
        String temp = StringUtils.trimAllWhitespace(newPassword);
        if (newPassword.length() != temp.length()) {
            return false;
        }

        if (newPassword.length() < PASSWORD_MIN_LENGTH || newPassword.length() > PASSWORD_MAX_LENGTH) {
            return false;
        }

        if (!newPassword.matches(PASSWORD_REGEX)) {
            return false;
        }

        return true;
    }
}
