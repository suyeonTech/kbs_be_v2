package com.bfriend.bfriend.room.dto.request;

import com.bfriend.bfriend.users.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Getter
public class RoomCreateDTO {
    //isReported의 초기값 0, joinPtc의 초기값 1로 전달값 불필요

    private final Users uid;

    private final LocalDateTime meetingTime;

    private final String roomName;

    private final String location;

    private final String restaurant;

    private final String foodType;

    private final Integer maxPtc;




}
