package com.github.axonsimple.command;

import com.github.axonsimple.core.ApproveParticipantCommand;
import com.github.axonsimple.core.ParticipantCheckRequestedEvent;
import com.github.axonsimple.core.RefuseParticipantCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class ParticipantApprovalSaga {

    private static Logger logger = LoggerFactory.getLogger(ParticipantApprovalSaga.class);


    @Autowired
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "roomId")
    public void handle(ParticipantCheckRequestedEvent event){
        logger.debug("[Saga] Checking participant join request {}", event);
        if (event.getParticipant().startsWith("p")){
            commandGateway.send(new ApproveParticipantCommand(event.getRoomId(), event.getParticipant()));
        }
        else {
            commandGateway.send(new RefuseParticipantCommand(event.getRoomId(), event.getParticipant()));
        }
        SagaLifecycle.end();
    }

}
