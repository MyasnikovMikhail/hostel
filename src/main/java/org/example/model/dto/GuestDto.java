package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeGender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GuestDto {

    private Long id;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer numFlat;

    private String name;

    private String surname;

    private String patronymic;

    private TypeGender gender;

    private LocalDate dateOfChange;

    private LocalDate dateOfAddition;

    public GuestDto(Integer numFlat, String name, String surname, String patronymic, TypeGender gender) {
        this.numFlat = numFlat;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.gender = gender;
    }
}
