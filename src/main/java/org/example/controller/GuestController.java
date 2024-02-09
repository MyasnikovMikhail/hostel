package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.GuestDto;
import org.example.service.GuestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {

    public final GuestService guestService;

    @PostMapping(value = "/guest-create")
    public void create(@RequestBody GuestDto guestDto) {
        guestService.create(guestDto);
    }

    @PostMapping(value = "/guest-read-all")
    public List<GuestDto> readAll() {
        return  guestService.readAll();
    }


}
