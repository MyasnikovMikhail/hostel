package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Room;
import org.example.model.TypeGender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GuestDto {

    private Long id;

    private Room numRoom;

    private String name;

    private String surname;

    private String patronymic;

    private TypeGender gender;

    private LocalDate dateOfChange;

    private LocalDate dateOfAddition;
}
