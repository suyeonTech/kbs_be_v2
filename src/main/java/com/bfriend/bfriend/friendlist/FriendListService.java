package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.FriendAddRequest;
import com.bfriend.bfriend.users.Users;
import com.bfriend.bfriend.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    }
}
