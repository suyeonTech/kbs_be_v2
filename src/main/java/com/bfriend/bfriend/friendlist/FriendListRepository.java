package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse;
import com.bfriend.bfriend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {
    @Query("SELECT new com.bfriend.bfriend.friendlist.dto.response.FriendsListResponse(f.addedUid.uid, f.addedUid.nickname, f.addedUid.profile) " +
            "FROM FriendList f " +
            "WHERE f.addUid = :users")
    List<FriendsListResponse> findByAddUid(Users users);
}
