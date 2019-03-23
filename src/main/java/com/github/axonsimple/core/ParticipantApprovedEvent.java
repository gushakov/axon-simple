package com.github.axonsimple.core;

import lombok.Value;

@Value
public class ParticipantApprovedEvent {

    private final String roomId;

    private final String participant;
}
