package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.TypeGender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GuestUpdDto {

    @NotNull
    @Min(1)
    @Max(100)
    private Byte numFlat;

    private String name;

    private String surname;

    private String patronymic;

    private TypeGender gender;

}
