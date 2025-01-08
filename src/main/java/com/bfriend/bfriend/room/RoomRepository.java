package com.bfriend.bfriend.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    public Room findByRid(Long rid);

    //검색 키워드를 포함하는 방이름, 지역, 식당, 음식 종류를 가진 모임방을 모두 검색
    @Query("select "
            + "distinct r "
            + "from Room r "
            + "where "
            + "r.roomName like %:kw% "
            + "or r.location like %:kw% "
            + "or r.restaurant like %:kw% "
            + "or r.foodType like %:kw% "
    )
    List<Room> findAllByKeyword(@Param("kw") String kw);

}
