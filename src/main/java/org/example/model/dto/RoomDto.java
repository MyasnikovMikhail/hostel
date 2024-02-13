package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeComfort;
import org.example.model.TypeGender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class RoomDto {

    private Long id;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer floor;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer flat;

    private TypeGender typeGender;

    private TypeComfort typeComfort;

    @NotNull
    @Min(1)
    private int numberOfSeats;

    private int totalSeats = numberOfSeats;

    private LocalDate dateOfChange;

    private LocalDate dateOfAddition;

    public RoomDto(int floor, int flat, TypeGender typeGender, TypeComfort typeComfort, int numberOfSeats) {
        this.floor = floor;
        this.flat = flat;
        this.typeGender = typeGender;
        this.typeComfort = typeComfort;
        this.numberOfSeats = numberOfSeats;
        this.totalSeats = numberOfSeats;
    }
}
