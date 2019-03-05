package com.github.axonsimple.rest;

import com.github.axonsimple.core.CreateRoomCommand;
import com.github.axonsimple.core.JoinRoomCommand;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
public class CommandController {

    private final CommandGateway commandGateway;

    @PostMapping("/rooms")
    public Future<String> createChatRoom(@RequestBody @Valid Room room) {
        return commandGateway.send(new CreateRoomCommand(room.getRoomId(), room.getName()));
    }

    @PostMapping("/rooms/{roomId}/participants")
    public Future<Void> joinChatRoom(@PathVariable String roomId, @RequestBody @Valid Participant participant) {
        return commandGateway.send(new JoinRoomCommand(roomId, participant.getName()));
    }

    @Data
    public static class Room {

        private String roomId;
        @NotEmpty
        private String name;

    }

    @Data
    public static class Participant {

        @NotEmpty
        private String name;

    }

}
