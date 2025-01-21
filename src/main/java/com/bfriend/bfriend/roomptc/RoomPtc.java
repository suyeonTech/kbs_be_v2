package com.bfriend.bfriend.roomptc;

import com.bfriend.bfriend.room.Room;
import com.bfriend.bfriend.users.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class RoomPtc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @ManyToOne
    private Room rid;

    @ManyToOne
    private Users uid;
}
