package com.bfriend.bfriend.users.dto.request;

import static com.bfriend.bfriend.utils.constants.ValidationConstants.*; // ValidationConstants의 상수를 바로 사용
import com.bfriend.bfriend.utils.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestJoinDTO {

    @NotBlank(message = "닉네임은"+VALIDATION_MESSAGE)
    @Size(min=2, max=10, message="닉네임은 2자 이상, 10자 이하여야 합니다.")
    @Pattern(
            regexp = NICKNAME_REGEXP,
            message = "닉네임은 한글, 영문, 숫자로만 구성될 수 있습니다."
    )
    private String nickname;

    @NotBlank(message = "비밀번호는"+VALIDATION_MESSAGE)
    @Size(min=8, max=15, message="비밀번호는 8자 이상, 15자 이하여야 합니다.")
    @Pattern(
            regexp = PASSWORD_REGEXP,
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    private Gender gender;

    private Integer age;

    @NotBlank(message="이메일은"+VALIDATION_MESSAGE)
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

}
