package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.UserIdRequest;
import com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse;
import com.bfriend.bfriend.users.Users;
import com.bfriend.bfriend.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendListService {

    private final UsersRepository usersRepository;
    private final FriendListRepository friendListRepository;

    public ResponseEntity<List<FriendsListResponse>> showFriendsList(UserIdRequest request) {
        Users users = usersRepository.findByUid(request.getUid())
                .orElseThrow();

        List<FriendsListResponse> friendsList = friendListRepository.findByAddUid(users);

        return ResponseEntity.ok(friendsList);
    }
}
