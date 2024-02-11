package org.example.repos;

import org.example.model.Room;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.example.model.dto.RoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findRoomByFlat(int flat);

    List<Room> findAllByTypeGender(TypeGender typeGender);

    List<Room> findAllByTypeComfort(TypeComfort typeComfort);
}
