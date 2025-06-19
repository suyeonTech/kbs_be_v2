package com.bfriend.bfriend.users.repository;

import com.bfriend.bfriend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bfriend.bfriend.friendlist.dto.response.FriendProfileResponse;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUid(Long uid);

    Optional<Users> findByEmail(String email);

    @Query("SELECT new com.bfriend.bfriend.friendlist.dto.response.FriendProfileResponse(u.profile, u.nickname, u.email, u.age, u.gender) " +
            "FROM Users u WHERE u.uid = :uid")
    List<FriendProfileResponse> findUserProfileByUid(Long uid);
}
