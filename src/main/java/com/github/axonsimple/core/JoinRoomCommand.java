package com.github.axonsimple.core;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class JoinRoomCommand {

    @TargetAggregateIdentifier
    private final String roomId;

    private final String participant;

}
