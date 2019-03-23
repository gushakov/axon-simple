package com.github.axonsimple.command;

import com.github.axonsimple.core.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Saga
public class ParticipantApprovalSaga {

    private static Logger logger = LoggerFactory.getLogger(ParticipantApprovalSaga.class);

    private List<String> participantsToBeApproved = new ArrayList<>();

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "roomId")
    public void handle(ApprovalStartedEvent event) {
        logger.debug("[Saga] Handle {}", event);
        if (!participantsToBeApproved.contains(event.getParticipant())) {
            logger.debug("[Saga] Adding participant {} to be approved", event.getParticipant());
            participantsToBeApproved.add(event.getParticipant());
        }

    }

    @SagaEventHandler(associationProperty = "roomId")
    public void handle(ApprovalFinishedEvent event) {
        logger.debug("[Saga] Handle {}", event);
        if (participantsToBeApproved.remove(event.getParticipant())) {
            commandGateway.send(new ApproveParticipantCommand(event.getRoomId(), event.getParticipant()));
            logger.debug("[Saga] Participant {} is approved", event.getParticipant());
            if (participantsToBeApproved.isEmpty()) {
                logger.debug("[Saga] Ending saga for room {}", event.getRoomId());
                SagaLifecycle.end();
            }
        }
    }

    @SagaEventHandler(associationProperty = "roomId")
    public void handle(ParticipantRefusedEvent event) {
        logger.debug("[Saga] Handle {}", event);
        if (participantsToBeApproved.remove(event.getParticipant())) {
            if (participantsToBeApproved.isEmpty()) {
                logger.debug("[Saga] Ending saga for room {}", event.getRoomId());
                SagaLifecycle.end();
            }
        }
    }

}
