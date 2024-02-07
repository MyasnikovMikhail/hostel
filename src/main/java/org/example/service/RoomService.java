package org.example.service;

import org.example.model.dto.RoomDto;

import java.util.List;

public interface RoomService {
    void create(RoomDto roomDto);

    List<RoomDto> readAll();


}
