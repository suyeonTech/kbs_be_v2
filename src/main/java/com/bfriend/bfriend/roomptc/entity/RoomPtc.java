package com.bfriend.bfriend.roomptc.entity;

import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.users.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomPtc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @ManyToOne
    private Room rid;

    @ManyToOne
    private Users uid;
}
