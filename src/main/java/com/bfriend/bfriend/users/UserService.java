package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String checkEmail(CheckEmailRequest request) {
        Users users = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(IllegalArgumentException::new);

        return "success";
    }
}
