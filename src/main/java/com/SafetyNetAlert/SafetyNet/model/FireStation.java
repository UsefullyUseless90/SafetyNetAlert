package com.SafetyNetAlert.SafetyNet.model;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "fireStations")


public class FireStation {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "station")
    private int station;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Person person;

    @Override
    public String toString() {
        return this.getId() + ", " + this.getAddress() + ", "+ this.getStation();
    }
}
