package org.example.service;

import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.GuestDto;
import org.example.model.dto.GuestUpdDto;

import java.util.List;

public interface GuestService {

    void create(GuestDto guestDto);

    List<GuestDto> readAll();

    List<GuestDto> readAll(TypeGender gender);

    List<GuestDto> readAll(int roomFlat);

    List<GuestDto> readAll(TypeComfort roomTypeComfort);

    void deleteGuest(Long id);

    void update(Long id, GuestUpdDto guestUpdDto);
}
