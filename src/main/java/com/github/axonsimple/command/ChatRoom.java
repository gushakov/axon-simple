package com.github.axonsimple.command;

import com.github.axonsimple.core.*;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Aggregate
@Entity
@Data
public class ChatRoom {

    private static Logger logger = LoggerFactory.getLogger(ChatRoom.class);

    @AggregateIdentifier
    @Id
    private String roomId;

    private String name;

    @ElementCollection
    private List<String> participants = new ArrayList<>();

    public ChatRoom() {
    }

    @CommandHandler
    public ChatRoom(CreateRoomCommand command) {
        logger.debug("[Aggregate][Command] Handle command: {}", command);
        AggregateLifecycle.apply(new RoomCreatedEvent(command.getRoomId(), command.getName()));
    }

    @CommandHandler
    public void handle(StartApprovalCommand command) {
        logger.debug("[Aggregate][Command] Handle command: {}", command);
        AggregateLifecycle.apply(new ApprovalStartedEvent(command.getRoomId(), command.getParticipant()));
    }

    @CommandHandler
    public void handle(FinishApprovalCommand command) {
        logger.debug("[Aggregate][Command] Handle command: {}", command);
        AggregateLifecycle.apply(new ApprovalFinishedEvent(command.getRoomId(), command.getParticipant()));
    }

    @CommandHandler
    public void handle(ApproveParticipantCommand command) {
        logger.debug("[Aggregate][Command] Handle command: {}", command);
        if (!participants.contains(command.getParticipant())) {
            AggregateLifecycle.apply(new ParticipantJoinedEvent(command.getParticipant(), command.getRoomId()));
        }
    }

    @CommandHandler
    public void handle(RefuseParticipantCommand command) {
        logger.debug("[Aggregate][Command] Handle command: {}", command);
        logger.debug("[Aggregate][Command] Refuse participant {}", command.getParticipant());
    }

    @EventHandler
    public void on(RoomCreatedEvent event) {
        logger.debug("[Aggregate][Event] On event: {}", event);
        this.roomId = event.getRoomId();
        this.name = event.getName();
    }

    @EventHandler
    public void on(ParticipantJoinedEvent event) {
        logger.debug("[Aggregate][Event] On event {}",
                event.getParticipant(), this.roomId, this.participants.size());
        participants.add(event.getParticipant());
    }

}
