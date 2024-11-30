package com.bfriend.bfriend.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 20, nullable = false)
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
}
