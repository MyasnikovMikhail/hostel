package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.RoomDto;
import org.example.model.dto.RoomUpdDto;
import org.example.service.RoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "/room-create")
    public void create(@Valid @RequestBody RoomDto roomDto) {
        roomDto.setDateOfAddition(LocalDate.now());
        roomDto.setDateOfChange(LocalDate.now());
        roomService.create(roomDto);
    }

    @GetMapping(value = "/room-read-all")
    public List<RoomDto> readAll() {
        return roomService.readAll();
    }

    @GetMapping(value = "/room-read-all/gender/{gender}")
    public List<RoomDto> readAll(@PathVariable(name = "gender")TypeGender typeGender) {
        return roomService.readAll(typeGender);
    }

    @GetMapping(value = "/room-read-all/comfort/{typeComfort}")
    public List<RoomDto> readAll(@PathVariable(name = "typeComfort")TypeComfort typeComfort) {
        return roomService.readAll(typeComfort);
    }

    @GetMapping(value = "/room-read-all/available-seats")
    public List<RoomDto> readAllNumberOfSeats() {
        return roomService.readAllNumberOfSeats();
    }

    @DeleteMapping(value = "/rooms/{numFlat}")
    public void delete(@PathVariable(name = "numFlat") int numFlat) {
        roomService.deleteRoom(numFlat);
    }

    @PutMapping(value = "/rooms/{numFlat}")
    public void update( @PathVariable(name = "numFlat") int numFlat,@RequestBody RoomUpdDto roomUpdDto) {
        roomService.update(numFlat, roomUpdDto);
    }
}
