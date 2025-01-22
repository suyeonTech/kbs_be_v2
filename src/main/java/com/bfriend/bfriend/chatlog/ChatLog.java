package com.bfriend.bfriend.chatlog;

import com.bfriend.bfriend.roomptc.RoomPtc;

import com.bfriend.bfriend.users.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ChatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    //임의로 추가
    @ManyToOne
    private Users uid;

    @ManyToOne
    private RoomPtc pid;

    private LocalDateTime timeNow;

    @Column(length = 100)
    private String chatContent;
}
