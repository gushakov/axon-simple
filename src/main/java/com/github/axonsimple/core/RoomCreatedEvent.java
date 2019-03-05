package com.github.axonsimple.core;

import lombok.Value;

@Value
public class RoomCreatedEvent {

    private final String roomId;

    private final String name;

}
