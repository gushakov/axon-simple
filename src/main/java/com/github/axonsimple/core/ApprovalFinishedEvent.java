package com.github.axonsimple.core;

import lombok.Value;

@Value
public class ApprovalFinishedEvent {

    private final String roomId;

    private final String participant;
}
