package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.ChangePasswordRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/changepw")
    public String changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request);
    }
}
