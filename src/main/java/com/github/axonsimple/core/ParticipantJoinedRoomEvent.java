package com.github.axonsimple.core;

import lombok.Value;

@Value
public class ParticipantJoinedRoomEvent {

    private final String participant;

    private final String roomId;

}
