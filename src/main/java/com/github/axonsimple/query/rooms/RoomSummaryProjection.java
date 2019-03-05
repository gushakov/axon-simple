package com.github.axonsimple.query.rooms;

import com.github.axonsimple.core.AllRoomsQuery;
import com.github.axonsimple.core.ParticipantJoinedRoomEvent;
import com.github.axonsimple.core.RoomCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoomSummaryProjection {

    private static Logger logger = LoggerFactory.getLogger(RoomSummaryProjection.class);

    private final RoomSummaryRepository roomSummaryRepository;

    public RoomSummaryProjection(RoomSummaryRepository roomSummaryRepository) {
        this.roomSummaryRepository = roomSummaryRepository;
    }

    @EventHandler
    public void on(RoomCreatedEvent event){
        logger.debug("[Query][Rooms][Event] On event: {}", event);
        roomSummaryRepository.save(new RoomSummary(event.getRoomId(), event.getName()));
    }

    @EventHandler
    public void on(ParticipantJoinedRoomEvent event){
        logger.debug("[Query][Rooms][Event] On event: {}", event);
        Optional<RoomSummary> roomSummary = roomSummaryRepository.findById(event.getRoomId());

        roomSummary
                .ifPresent(summary -> {
                    summary.addParticipant();
                    roomSummaryRepository.save(summary);});

    }

    @QueryHandler
    public List<RoomSummary> handle(AllRoomsQuery query){
        logger.debug("[Query][Rooms] Handle query: {}", query);
        return roomSummaryRepository.findAll();

    }

}