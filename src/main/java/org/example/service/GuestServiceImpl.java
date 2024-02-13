package org.example.service;

import org.example.exception.IncorrectGender;
import org.example.exception.NoAvailableSeats;
import org.example.exception.NoSuchObjectException;
import org.example.model.Guest;
import org.example.model.Room;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.GuestDto;
import org.example.model.dto.GuestUpdDto;
import org.example.repos.GuestRepo;
import org.example.repos.RoomRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        Optional<Room> room = roomRepo.findRoomByFlat(guestDto.getNumFlat());
        if (!room.isPresent()) throw new NoSuchObjectException("Квартиры под таким номером не существует");
        if (room.get().getTypeGender().equals(guestDto.getGender())) {
            if (room.get().getNumberOfSeats() > 0) {
                room.get().setNumberOfSeats(room.get().getNumberOfSeats() - 1);
                room.get().setDateOfChange(LocalDate.now());
                roomRepo.save(room.get());
                guestRepo.save(guestDtoToGuest(guestDto));
            } else {
                throw new NoAvailableSeats("В комнате нет свободных мест");
            }
        } else {
            throw new IncorrectGender("Пол постояльца отличается от типа комнаты ");
        }
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

    @Transactional
    @Override
    public List<GuestDto> readAll(TypeGender gender) {
        return guestRepo
                .findAllByGender(gender)
                .stream()
                .map(this::convertToGuestDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<GuestDto> readAll(int roomFlat) {
        return guestRepo
                .findAllByRoom_Flat(roomFlat)
                .stream()
                .map(this::convertToGuestDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<GuestDto> readAll(TypeComfort roomTypeComfort) {
        return guestRepo
                .findAllByRoom_TypeComfort(roomTypeComfort)
                .stream()
                .map(this::convertToGuestDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteGuest(Long id) {
        Optional<Guest> guest = guestRepo.findById(id);
        Optional<Room> room = roomRepo.findById(guest.get().getRoom().getId());
        room.get().setNumberOfSeats(room.get().getNumberOfSeats() + 1);
        room.get().setDateOfChange(LocalDate.now());
        roomRepo.save(room.get());
        guestRepo.deleteById(id);

    }

    @Transactional
    @Override
    public void update(Long id, GuestUpdDto guestUpdDto) {
        Guest guest = guestRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        Optional<Room> room = roomRepo.findRoomByFlat(guestUpdDto.getNumFlat());
        if (room.isPresent() && room.get().getNumberOfSeats() > 0) {
            guest.setRoom(room.get());
            room.get().setNumberOfSeats(room.get().getNumberOfSeats() + 1);
        } else {
            guest.setRoom(guest.getRoom());
        }
        guest.setName(guestUpdDto.getName() == null ? guest.getName() : guestUpdDto.getName());
        guest.setSurname(guestUpdDto.getSurname() == null ? guest.getSurname() : guestUpdDto.getSurname());
        guest.setPatronymic(guestUpdDto.getPatronymic() == null ? guest.getPatronymic() : guestUpdDto.getPatronymic());
        guest.setDateOfChange(LocalDate.now());
        Optional<Room> room2 = roomRepo.findById(guest.getRoom().getId());
        room2.get().setNumberOfSeats(room.get().getNumberOfSeats() - 1);
        room2.get().setDateOfChange(LocalDate.now());
        roomRepo.save(room.get());
    }

    private Guest guestDtoToGuest(GuestDto guestDto) {
        Guest guest = new Guest();
        guest.setRoom(roomRepo.findRoomByFlat(guestDto.getNumFlat()).orElseThrow(EntityNotFoundException::new));
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
        guestDto.setNumFlat(guest.getRoom().getFlat());
        guestDto.setName(guest.getName());
        guestDto.setSurname(guest.getSurname());
        guestDto.setPatronymic(guest.getPatronymic());
        guestDto.setGender(guest.getGender());
        guestDto.setDateOfChange(guest.getDateOfChange());
        guestDto.setDateOfAddition(guest.getDateOfAddition());
        return guestDto;
    }


}
