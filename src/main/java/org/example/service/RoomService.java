package org.example.service;

import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.RoomDto;
import org.example.model.dto.RoomUpdDto;

import java.util.List;

public interface RoomService {

    void create(RoomDto roomDto);

    List<RoomDto> readAll();

    List<RoomDto> readAll(TypeGender typeGender);

    List<RoomDto> readAll(TypeComfort typeComfort);

    List<RoomDto> readAllNumberOfSeats();

    void deleteRoom(int numFlat);

    void update(int numFlat, RoomUpdDto roomUpdDto);

}
