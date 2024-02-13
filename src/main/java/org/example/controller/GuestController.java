package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.GuestDto;
import org.example.model.dto.GuestUpdDto;
import org.example.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {

    public final GuestService guestService;

    @PostMapping(value = "/guest-create")
    public void create(@RequestBody GuestDto guestDto) {
        guestDto.setDateOfAddition(LocalDate.now());
        guestDto.setDateOfChange(LocalDate.now());
        guestService.create(guestDto);
    }

    @GetMapping(value = "/guest-read-all")
    public List<GuestDto> readAll() {
        return guestService.readAll();
    }

    @GetMapping(value = "/guest-read-all/gender/{gender}")
    public List<GuestDto> readAll(@PathVariable(name = "gender") TypeGender gender) {
        return guestService.readAll(gender);
    }

    @GetMapping(value = "/guest-read-all/{roomFlat}")
    public List<GuestDto> readAll(@PathVariable(name = "roomFlat") int roomFlat) {
        return guestService.readAll(roomFlat);
    }

    @GetMapping(value = "/guest-read-all/comfort/{typeComfort}")
    public List<GuestDto> readAll(@PathVariable(name = "typeComfort") TypeComfort roomTypeComfort) {
        return guestService.readAll(roomTypeComfort);
    }

    @DeleteMapping(value = "/guests/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        guestService.deleteGuest(id);
    }

    @PutMapping(value = "/guests/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody GuestUpdDto guestUpdDto) {
        guestService.update(id, guestUpdDto);
    }

}
