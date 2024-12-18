package com.bfriend.bfriend.users.dto;

import com.bfriend.bfriend.utils.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {

    private String nickname;
    private String password;
    private Gender gender;
    private Integer age;
    private String email;

}
