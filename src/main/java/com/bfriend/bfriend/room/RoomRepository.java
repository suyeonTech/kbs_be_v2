package com.bfriend.bfriend.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    public Room findByRid(Long rid);

}
