package com.bfriend.bfriend.room.mapper;

import com.bfriend.bfriend.users.dto.UserDTO;
import com.bfriend.bfriend.users.entity.Users;

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

    public static List<UserDTO> toUsersDTO(List<Users> users) {

        return users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getUid())
                        .nickname(user.getNickname())
                        .profile(user.getProfile())
                        .build())
                .collect(Collectors.toList());
    }
}
