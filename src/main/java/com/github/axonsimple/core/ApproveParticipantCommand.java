package com.github.axonsimple.core;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ApproveParticipantCommand {

    @TargetAggregateIdentifier
    private final String roomId;

    private String participant;

}
