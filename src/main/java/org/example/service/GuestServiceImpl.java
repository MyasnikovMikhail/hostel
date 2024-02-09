package org.example.service;

import org.example.model.Guest;
import org.example.model.Room;
import org.example.model.dto.GuestDto;
import org.example.repos.GuestRepo;
import org.example.repos.RoomRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepo guestRepo;

    private final RoomRepo roomRepo;

    public GuestServiceImpl(GuestRepo guestRepo, RoomRepo roomRepo) {
        this.guestRepo = guestRepo;
        this.roomRepo = roomRepo;
    }

    @Transactional
    @Override
    public void create(GuestDto guestDto) {
        Room room = roomRepo.findById(guestDto.getRoom());
    if(room.getTypeGender().equals(guestDto.getGender()) && room.getNumberOfSeats() > 0)
        guestRepo.save(guestDtoToGuest(guestDto));
    }

    @Transactional
    @Override
    public List<GuestDto> readAll() {
        return guestRepo
                .findAll()
                .stream()
                .map(this::convertToGuestDTO)
                .collect(Collectors.toList());
    }

    private Guest guestDtoToGuest(GuestDto guestDto) {
        Guest guest = new Guest();
        guest.setRoom(guestDto.getRoom());
        guest.setName(guestDto.getName());
        guest.setSurname(guestDto.getSurname());
        guest.setPatronymic(guestDto.getPatronymic());
        guest.setGender(guestDto.getGender());
        guest.setDateOfChange(guestDto.getDateOfChange());
        guest.setDateOfAddition(guestDto.getDateOfAddition());
        return guest;
    }


    private GuestDto convertToGuestDTO(Guest guest) {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setRoom(guest.getRoom());
        guestDto.setName(guest.getName());
        guestDto.setSurname(guest.getSurname());
        guestDto.setPatronymic(guest.getPatronymic());
        guestDto.setGender(guest.getGender());
        guestDto.setDateOfChange(guest.getDateOfChange());
        guestDto.setDateOfAddition(guest.getDateOfAddition());
        return guestDto;
    }


}
