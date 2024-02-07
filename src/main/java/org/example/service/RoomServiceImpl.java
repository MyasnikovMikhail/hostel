package org.example.service;

import org.example.model.Room;
import org.example.model.dto.RoomDto;
import org.example.repos.RoomRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{

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
    public List<RoomDto> readAll() {
        return roomRepo
                .findAll()
                .stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }



    private Room roomDtoToRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setFlat(roomDto.getFlat());
        room.setTypeGender(roomDto.getTypeGender());
        room.setTypeComfort(roomDto.getTypeComfort());
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
