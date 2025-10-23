package com.bfriend.bfriend.roomptc;

import com.bfriend.bfriend.room.Room;
import com.bfriend.bfriend.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
