package com.bfriend.bfriend.room;

import com.bfriend.bfriend.roomptc.RoomPtc;
import com.bfriend.bfriend.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @ManyToOne
    private Users masterUid;

    private LocalDateTime meetingTime;

    @Column(length = 20)
    private String roomName;

    private String location;

    private String restaurant;

    private String foodType;

    private Boolean isReported;

    private Integer maxPtc;

    private Integer joinPtc;

    @OneToMany(mappedBy = "rid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomPtc> participants;

    public void addPtc() {
        this.joinPtc++;
    }

    public void deletePtc(){
        this.joinPtc--;
    }

}
