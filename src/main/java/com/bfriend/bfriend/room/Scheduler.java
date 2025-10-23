package com.bfriend.bfriend.room;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler{

    private final RoomService roomService;

    //1분마다 실행
    @Scheduled(cron = "0 * * * * *")
    public void deleteExpiredRooms() {
        roomService.deleteExpiredRooms();
    }
}