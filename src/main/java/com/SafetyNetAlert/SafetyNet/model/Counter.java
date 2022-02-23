package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "counter")
public class Counter {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "numberOfChildren", nullable = true)
    private int numberOfChildren;
    @Column( name = "numberOfAdults", nullable = false)
    private int numberOfAdults;
    @Column( name = "houseHoldCount", nullable = false)
    private int houseHoldCount;
    @Column( name = "houseHoldMember", nullable = false)
    private String houseHoldMember; // ArrayList?? contains people at the same address (same name + same address)


}
