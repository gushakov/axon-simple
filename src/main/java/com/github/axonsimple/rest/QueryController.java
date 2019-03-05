package com.github.axonsimple.rest;

import com.github.axonsimple.core.AllRoomsQuery;
import com.github.axonsimple.query.rooms.RoomSummary;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

@RestController
public class QueryController {

    private final QueryGateway gateway;

    public QueryController(QueryGateway gateway) {
        this.gateway = gateway;
    }

    @GetMapping("rooms")
    public Future<List<RoomSummary>> listRooms() {
       return gateway.query(new AllRoomsQuery(), ResponseTypes.multipleInstancesOf(RoomSummary.class));
    }

}
