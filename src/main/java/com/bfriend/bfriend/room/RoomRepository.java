package com.bfriend.bfriend.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    public Room findByRid(Integer rid);

}
