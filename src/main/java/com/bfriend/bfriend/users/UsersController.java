package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UserService userService;

    @PostMapping("/changepw")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request);
    }
}
