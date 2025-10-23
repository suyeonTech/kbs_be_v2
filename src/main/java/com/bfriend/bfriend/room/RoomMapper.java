package com.bfriend.bfriend.room;

import com.bfriend.bfriend.room.dto.MyRoomDTO;
import com.bfriend.bfriend.users.dto.UserDTO;
import com.bfriend.bfriend.users.Users;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {
    public static UserDTO toUserDTO(Users user){
        UserDTO userDTO = UserDTO.builder()
                .id(user.getUid())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .build();

        return userDTO;
    }

    public static List<UserDTO> toUserDTOs(List<Users> users) {

        return users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getUid())
                        .nickname(user.getNickname())
                        .profile(user.getProfile())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<MyRoomDTO> toRoomDTOs(List<Room> rooms) {

        return rooms.stream()
                .map(room -> MyRoomDTO.builder()
                        .rid(room.getRid())
                        .roomName(room.getRoomName())
                        .meetingTime(room.getMeetingTime())
                        .location(room.getLocation())
                        .restaurant(room.getRestaurant())
                        .maxPtc(room.getMaxPtc())
                        .joinPtc(room.getJoinPtc())
                        .build())
                .collect(Collectors.toList());
    }
}
