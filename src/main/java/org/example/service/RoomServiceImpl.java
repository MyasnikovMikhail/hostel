package org.example.service;

import org.example.exception.CannotDeleteObjectException;
import org.example.exception.ErrorNumberGuests;
import org.example.exception.NoSuchObjectException;
import org.example.model.Room;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.RoomDto;
import org.example.model.dto.RoomUpdDto;
import org.example.repos.RoomRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Transactional
    @Override
    public void create(RoomDto room) {
        roomRepo.save(roomDtoToRoom(room));
    }

    @Transactional
    @Override
    public List<RoomDto> readAllNumberOfSeats() {
        return roomRepo
                .findAll()
                .stream()
                .filter(i ->(i.getTotalSeats() - i.getNumberOfSeats() != i.getTotalSeats()))
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<RoomDto> readAll(TypeGender typeGender) {
        return roomRepo
                .findAllByTypeGender(typeGender)
                .stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<RoomDto> readAll(TypeComfort typeComfort) {
        return roomRepo
                .findAllByTypeComfort(typeComfort)
                .stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<RoomDto> readAll() {
        return roomRepo
                .findAll()
                .stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteRoom(int numFlat) {
        Room room = roomRepo.findRoomByFlat(numFlat).orElseThrow(EntityNotFoundException::new);
        if (room.getNumberOfSeats() == room.getTotalSeats()) {
            roomRepo.deleteById(room.getId());
        } else {
            throw new CannotDeleteObjectException("Еще не все жильцы выехали из комнаты");
        }
    }

    @Transactional
    @Override
    public void update(int numFlat, RoomUpdDto roomUpdDto) {
        Room room = roomRepo.findRoomByFlat(numFlat).orElseThrow(EntityNotFoundException::new);
        room.setFlat(roomUpdDto.getFlat() == 0 ||  roomRepo.findRoomByFlat(roomUpdDto.getFlat()).isPresent() ? room.getFlat() : roomUpdDto.getFlat());
        room.setTypeGender(roomUpdDto.getTypeGender() == null || room.getTotalSeats().intValue() != room.getNumberOfSeats().intValue() ? room.getTypeGender() : roomUpdDto.getTypeGender());
        room.setTypeComfort(roomUpdDto.getTypeComfort() == null ? room.getTypeComfort() : roomUpdDto.getTypeComfort());

        if(roomUpdDto.getNumberOfSeats() >= room.getNumberOfSeats()) {
            room.setNumberOfSeats(roomUpdDto.getNumberOfSeats() - (room.getTotalSeats() - room.getNumberOfSeats()));
            room.setTotalSeats(roomUpdDto.getNumberOfSeats());
        } else {
            throw new ErrorNumberGuests("Количество жителей превышает новое число мест. Переселите или удалите жильцов из комнаты.");
        }
        room.setDateOfChange(LocalDate.now());
    }


    private Room roomDtoToRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setFlat(roomDto.getFlat());
        room.setTypeGender(roomDto.getTypeGender());
        room.setTypeComfort(roomDto.getTypeComfort());
        room.setTotalSeats(roomDto.getNumberOfSeats());
        room.setNumberOfSeats(roomDto.getNumberOfSeats());
        room.setDateOfChange(roomDto.getDateOfChange());
        room.setDateOfAddition(roomDto.getDateOfAddition());
        return room;
    }


    private RoomDto convertToRoomDTO(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setFloor(room.getFloor());
        roomDto.setFlat(room.getFlat());
        roomDto.setTypeGender(room.getTypeGender());
        roomDto.setTypeComfort(room.getTypeComfort());
        roomDto.setNumberOfSeats(room.getNumberOfSeats());
        roomDto.setDateOfChange(room.getDateOfChange());
        roomDto.setDateOfAddition(room.getDateOfAddition());
        return roomDto;
    }
}
