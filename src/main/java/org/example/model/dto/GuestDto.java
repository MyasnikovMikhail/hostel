package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Room;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GuestDto {

    private Long id;

    private Room team;

    private String name;

    private String surname;

    private String patronymic;

    private String gender;

    private LocalDate dateOfChange;

    private LocalDate birthdate;
}
