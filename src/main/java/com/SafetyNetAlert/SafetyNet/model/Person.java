package com.SafetyNetAlert.SafetyNet.model;

import com.SafetyNetAlert.SafetyNet.dto.Adults;
import com.SafetyNetAlert.SafetyNet.dto.Child;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "persons")

public class Person {



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();}

        @Id
        @Column(name = "id", nullable = false)
        private Long id;
        @Column(name = "firstName")
        private String firstName;
        @Column(name = "lastName")
        private String lastName;
        @Column(name = "phone")
        private String phone;
        @Column(name = "zip")
        private String zip;
        @Column(name = "address")
        private String address;
        @Column(name = "city")
        private String city;
        @Column(name = "email")
        private String email;
        @Column(name = "birthDate", nullable = false)
        private String birthDate;
        @Column(name = "age", nullable = false)
        private int age;
        @Column(name = "stationNumber", nullable = false)
        private int stationNumber;

        private int adults;
        private int children;

        private List<Person> alertChild;
        private List<Person> alertAdults;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private MedicalRecord medicalRecord;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private FireStation fireStation;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Counter counter;

    @Override
    public String toString() {
        return this.getId() + ", " + this.getFirstName() + ", "+ this.getLastName();
    }

}
