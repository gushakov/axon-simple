package com.github.axonsimple.query.rooms;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class RoomSummary {

    @Id
    private String roomId;
    private String name;
    private int participants;

    public RoomSummary(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void addParticipant() {
        this.participants++;
    }

    public void removeParticipant() {
        this.participants--;
    }

}