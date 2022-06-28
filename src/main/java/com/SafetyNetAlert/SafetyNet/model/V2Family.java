package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class V2Family {

    private String address;// firestation that covers
    private int houseHoldMembers;
    private int adults;
    private int children;
    private List<V2Person> personList;

    /**
     * @param address
     */
    public V2Family(String address) {
        this.address = address;
        this.houseHoldMembers = 0;
        this.adults = 0;//needs method to be calculated
        this.children = 0;//needs method to be calculated
        this.personList = new ArrayList<V2Person>();
    }

    /**
     * @param p
     */
    public void addPerson(Person p) throws IOException {
        this.getPersonList().add(new V2Person(p));
        this.houseHoldMembers++;

    }

    public void calculateAge() {

        for (V2Person p2 : getPersonList()) {
            for (int i = 0; i < personList.size(); i++) {
                if (getPersonList().get(i).getLastName().equals(p2.getLastName())
                        && getPersonList().get(i).getFirstName().equals(p2.getFirstName())) {
                    if (getPersonList().get(i).getAge() >= 18) {
                        this.adults++;
                    } else {
                        this.children++;
                    }
                }
            }
        }
    }
}