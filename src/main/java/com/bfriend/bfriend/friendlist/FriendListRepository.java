package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {
    List<FriendList> findByAddUid(Users users);
}
