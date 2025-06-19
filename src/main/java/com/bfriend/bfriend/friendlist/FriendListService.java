package com.bfriend.bfriend.friendlist;


import com.bfriend.bfriend.friendlist.dto.response.FriendProfileResponse;
import com.bfriend.bfriend.security.CustomUserDetails;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import com.bfriend.bfriend.utils.exceptions.NotFoundException;
import com.bfriend.bfriend.friendlist.dto.request.FriendAddRequest;
import com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
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

    public ResponseEntity<List<FriendsListResponse>> showFriendsList(CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USERS_UIDNOTFOUND, email));

        List<FriendsListResponse> friendsList = friendListRepository.findByAddUid(users);

        if(friendsList.isEmpty()) {
            log.info("사용자 ID {}에 추가된 친구가 없습니다.", users.getUid());
        }

        return ResponseEntity.ok(friendsList);
    }

    public ResponseEntity<List<FriendProfileResponse>> showFriendProfile (Long friendUid) {
        List<FriendProfileResponse> friendProfile = usersRepository.findUserProfileByUid(friendUid);

        return ResponseEntity.ok(friendProfile);
    }
}
