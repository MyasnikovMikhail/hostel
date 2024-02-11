package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;


@Getter
@Setter
@NoArgsConstructor
public class RoomUpdDto {

    private Integer flat;

    private TypeGender typeGender;

    private TypeComfort typeComfort;

    private Integer numberOfSeats;

    private Integer totalSeats = numberOfSeats;

}
