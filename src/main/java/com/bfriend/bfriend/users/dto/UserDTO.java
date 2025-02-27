package com.bfriend.bfriend.users.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {

    private Long id;
    private String nickname;
    private String profile;

}
