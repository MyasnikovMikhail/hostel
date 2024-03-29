package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "roomsIdSeq", sequenceName = "SEQ_SERVICE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomsIdSeq")
    private Long id;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "flat", unique = true)
    private Integer flat;

    @Column(name = "type_flat")
    @Enumerated(EnumType.STRING)
    private TypeGender typeGender;

    @Column(name = "type_comfort")
    @Enumerated(EnumType.STRING)
    private TypeComfort typeComfort;

    @Column(name = "number_seats")
    private Integer numberOfSeats;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name = "date_change")
    private LocalDate dateOfChange;

    @Column(name = "date_addition")
    private LocalDate dateOfAddition;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.REMOVE)
    private Set<Guest> guests;
}
