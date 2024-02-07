package org.example.service;

import org.example.model.dto.GuestDto;
import org.example.model.dto.RoomDto;

import java.util.List;

public interface GuestService {

    void create(GuestDto guestDto);

    List<GuestDto> readAll();
}
