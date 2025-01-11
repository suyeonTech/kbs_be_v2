package com.bfriend.bfriend.users;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder(toBuilder = true)
@Getter
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    private Boolean gender;

    private Integer age;

    @ColumnDefault("false")
    private Boolean isReported;

    @ColumnDefault("false")
    private Boolean isStopped;

    private String profile;

    protected Users() {
    }

    public Users(Long uid, String email, String password, String nickname, Boolean gender,
                 Integer age, Boolean isReported, Boolean isStopped, String profile) {

        this.uid = uid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.isReported = isReported;
        this.isStopped = isStopped;
        this.profile = profile;
    }
}
