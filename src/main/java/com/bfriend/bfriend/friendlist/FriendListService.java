package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.FriendAddRequest;
import com.bfriend.bfriend.users.Users;
import com.bfriend.bfriend.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bfriend.bfriend.friendlist.dto.request.UserIdRequest;
import com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendListService {

    private final UsersRepository usersRepository;
    private final FriendListRepository friendListRepository;


    public ResponseEntity<String> addFriend(FriendAddRequest request) {
        Users currentUserUid = usersRepository.findByUid(request.getUserId())
                .orElseThrow();
        Users targetUserUid = usersRepository.findByUid(request.getFriendId())
                .orElseThrow();

        FriendList addFriend = FriendList.builder()
                .addUid(currentUserUid)
                .addedUid(targetUserUid).
                build();

        friendListRepository.save(addFriend);

        return ResponseEntity.status(HttpStatus.CREATED).body("친구 추가 성공");

    public ResponseEntity<List<FriendsListResponse>> showFriendsList(UserIdRequest request) {
        Users users = usersRepository.findByUid(request.getUid())
                .orElseThrow();

        List<FriendsListResponse> friendsList = friendListRepository.findByAddUid(users);

        return ResponseEntity.ok(friendsList);
    }
}
