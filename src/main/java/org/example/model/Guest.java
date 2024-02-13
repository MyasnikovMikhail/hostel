package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guests")
public class Guest {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "guestsIdSeq", sequenceName = "SEQ_SERVICE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guestsIdSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private TypeGender gender;

    @Column(name = "date_change")
    private LocalDate dateOfChange;

    @Column(name = "date_addition")
    private LocalDate dateOfAddition;
}
