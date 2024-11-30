package com.bfriend.bfriend.room;

import com.bfriend.bfriend.users.Users;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @ManyToOne
    private Users uid;

    private LocalDateTime meetingTime;

    @Column(length = 20)
    private String roomName;

    private String location;

    private String restaurant;

    private String foodType;

    private Boolean isReported;

    private Integer maxPtc;

    private Integer joinPtc;
}
