package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.FriendAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/friend")
@RequiredArgsConstructor
@RestController
public class FriendListContoller {

    private final FriendListService friendListService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestBody FriendAddRequest request) {
        return friendListService.addFriend(request);
    }
}
