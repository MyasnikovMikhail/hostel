package org.example.repos;

import org.example.model.Guest;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {

    List<Guest> findAllByGender(TypeGender gender);

    List<Guest> findAllByRoom_Flat(int roomFlat);

    List<Guest> findAllByRoom_TypeComfort(TypeComfort roomTypeComfort);
}
