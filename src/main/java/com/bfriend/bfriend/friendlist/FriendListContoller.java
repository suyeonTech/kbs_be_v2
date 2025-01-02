package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.UserIdAndFriendIdRequest;
import lombok.RequiredArgsConstructor;
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
    public void addFriend(@RequestBody UserIdAndFriendIdRequest request) {
        friendListService.addFriend(request);
    }
}
