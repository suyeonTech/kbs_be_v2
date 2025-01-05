package com.bfriend.bfriend.users.repository;

import com.bfriend.bfriend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    //Boolean existsByNickname(String nickname);

    Optional<Users> findByPassword(String password);
    Optional<Users> findByNickname(String nickname);
    Optional<Users> findByEmail(String email);
}
