package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.FriendAddRequest;
import com.bfriend.bfriend.friendlist.dto.response.FriendProfileResponse;
import com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse;
import com.bfriend.bfriend.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/friend")
@RequiredArgsConstructor
@RestController
public class FriendListContoller {

    private final FriendListService friendListService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestBody FriendAddRequest request) {
        return friendListService.addFriend(request);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FriendsListResponse>> showFriendsList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return friendListService.showFriendsList(customUserDetails);
    }

    @GetMapping("/profile")
    public ResponseEntity<List<FriendProfileResponse>> showFriendProfile(@RequestParam("uid") Long friendUid) {
        return friendListService.showFriendProfile(friendUid);
    }
}
