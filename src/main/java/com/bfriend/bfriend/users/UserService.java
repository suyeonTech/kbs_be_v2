package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final MailService mailService;

    public UserService(UsersRepository usersRepository, MailService mailService) {
        this.usersRepository = usersRepository;
        this.mailService = mailService;
    }

    public String checkEmail(CheckEmailRequest request) {
        Users users = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(IllegalArgumentException::new);

        return mailService.sendMail(users.getEmail());
    }

    public String checkAuthenticationNumber(CheckAuthenticationNumberRequest request) {
        return mailService.checkAuthenticationNumber(request);
    }
}
