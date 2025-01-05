package com.bfriend.bfriend.users;

import com.bfriend.bfriend.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
