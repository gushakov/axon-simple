package com.github.axonsimple.core;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RefuseParticipantCommand {

    @TargetAggregateIdentifier
    private final String participant;

    private final String roomId;

}
