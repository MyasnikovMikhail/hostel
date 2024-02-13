package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class RoomUpdDto {

    @Min(1)
    @Max(100)
    private Integer flat;

    private TypeGender typeGender;

    private TypeComfort typeComfort;

    private Integer numberOfSeats;

    private Integer totalSeats = numberOfSeats;

    public RoomUpdDto(Integer flat, TypeGender typeGender, TypeComfort typeComfort, Integer numberOfSeats) {
        this.flat = flat;
        this.typeGender = typeGender;
        this.typeComfort = typeComfort;
        this.numberOfSeats = numberOfSeats;
        this.totalSeats = numberOfSeats;
    }
}
