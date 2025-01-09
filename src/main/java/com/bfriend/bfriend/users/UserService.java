package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final MailService mailService;

    public ResponseEntity<String> checkEmail(CheckEmailRequest request) {
        Users users = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(IllegalArgumentException::new);

        return mailService.sendMail(users.getEmail());
    }

    public ResponseEntity<String> checkAuthenticationNumber(CheckAuthenticationNumberRequest request) {
        return mailService.checkAuthenticationNumber(request);
    }
}
