package com.bfriend.bfriend.roomptc;

import com.bfriend.bfriend.room.Room;

import com.bfriend.bfriend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomPtcRopository extends JpaRepository<RoomPtc, Long> {
    @Query("SELECT rp.uid FROM RoomPtc rp WHERE rp.rid = :room")
    List<Users> findUsersByRoom(@Param("room") Room room);
}
