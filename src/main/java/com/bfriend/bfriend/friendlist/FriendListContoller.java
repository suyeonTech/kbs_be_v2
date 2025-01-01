package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.UserIdRequest;
import com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/friend")
@RequiredArgsConstructor
@RestController
public class FriendListContoller {

    private final FriendListService friendListService;

    @PostMapping("/list")
    public List<FriendsListResponse> ShowFriendsList(@RequestBody UserIdRequest request) {
        return friendListService.ShowFriendsList(request);
    }
}
