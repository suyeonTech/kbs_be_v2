package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.request.UserIdAndFriendIdRequest;
import com.bfriend.bfriend.users.Users;
import com.bfriend.bfriend.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Service
public class FriendListService {

    private final UsersRepository usersRepository;
    private final FriendListRepository friendListRepository;

    public void addFriend(UserIdAndFriendIdRequest request) {
        Users addUid = usersRepository.findByUid(request.getUserId())
                .orElseThrow();
        Users addedUid = usersRepository.findByUid(request.getFriendId())
                .orElseThrow();

        FriendList addFriend = FriendList.builder()
                .addUid(addUid)
                .addedUid(addedUid).
                build();

        friendListRepository.save(addFriend);
    }
}
