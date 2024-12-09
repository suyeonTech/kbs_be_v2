package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/checkemail")
    public String authenticationEmail(@RequestBody CheckEmailRequest request) {
        return userService.checkEmail(request);
    }

    @PostMapping("/users/findpw")
    public String checkAuthenticationNumber(@RequestBody CheckAuthenticationNumberRequest request) {
        return userService.checkAuthenticationNumber(request);
    }
}
