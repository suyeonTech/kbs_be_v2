package com.bfriend.bfriend.users;

import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;
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

    @PostMapping("/checkemail")
    public ResponseEntity<String> authenticationEmail(@RequestBody CheckEmailRequest request) {
        return userService.checkEmail(request);
    }

    @PostMapping("/findpw")
    public ResponseEntity<String> checkAuthenticationNumber(@RequestBody CheckAuthenticationNumberRequest request) {
        return userService.checkAuthenticationNumber(request);
    }
}
