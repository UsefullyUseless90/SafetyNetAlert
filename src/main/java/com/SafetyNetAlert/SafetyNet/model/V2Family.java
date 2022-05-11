package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
     *
     * @param address
     */
    public V2Family(String address) {
        this.address = address;
        this.houseHoldMembers = 0;//needs method to be calculated
        this.adults = 0;//needs method to be calculated
        this.children = 0;//needs method to be calculated
        this.personList = new ArrayList<V2Person>();
    }

    /**
     *
     * @param p
     */
    public void addPerson(Person p) {
        this.getPersonList().add(new V2Person(p));
        this.houseHoldMembers ++;
    }

    /**
     *
     * @param p
     * @return
     */
    public int houseHoldCount(Person p) {
        this.getPersonList();
        this.houseHoldMembers = 0;
        for (int i = 0; i < personList.size(); i++) {

            if (this.getAddress().equals(p.getAddress())) {
                this.houseHoldMembers++;
            }
        }
        return houseHoldMembers;
    }

    /**
     *
     * @param birthDate
     * @param now
     * @return
     */
    public int calculateAge(LocalDate birthDate, LocalDate now) {
        this.getPersonList();
        this.adults = 0;
        this.children = 0;
        for (V2Person p2 : personList)
            birthDate = p2.getBirthdate();
        now = LocalDate.now();
         int difference = Period.between(birthDate,now).getYears();
        for (int i = 0; i < personList.size(); i++) {
            if(difference >= 18){
                this.adults = +1;
            }else {
                this.children = +1;
                break;
            }
        }

        return (this.children + this.adults);// (!) return à confirmer (!)
    }
}

