package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RoomDto {

    private Long id;

    private byte floor;

    private byte flat;

    private TypeGender typeGender;

    private TypeComfort typeComfort;

    private byte NumberOfSeats;

    private LocalDate dateOfChange;

    private LocalDate dateOfAddition;
}
