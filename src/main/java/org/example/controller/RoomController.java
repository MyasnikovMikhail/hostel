package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.RoomDto;
import org.example.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "/room-create")
    public void create(@RequestBody RoomDto roomDto) {
        roomService.create(roomDto);
    }

    @PostMapping(value = "/room-read-all")
    public List<RoomDto> readAll() {
        return roomService.readAll();
    }

}
