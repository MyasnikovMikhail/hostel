package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class GuestUpdDto {

    @Min(1)
    @Max(100)
    private Integer numFlat;

    private String name;

    private String surname;

    private String patronymic;

    public GuestUpdDto(Integer numFlat, String name, String surname, String patronymic) {
        this.numFlat = numFlat;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
}
