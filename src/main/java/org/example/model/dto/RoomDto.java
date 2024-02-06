package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeComfort;
import org.example.model.TypeFlat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RoomDto {

    private Long id;

    private byte floor;

    private byte flat;

    private TypeFlat typeFlat;

    private TypeComfort typeComfort;

    private TypeComfort NumberOfSeats;

    private LocalDate dateOfChange;

    private LocalDate birthdate;
}
