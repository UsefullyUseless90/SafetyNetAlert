package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChildListTest {

    ChildAlert cA = new ChildAlert();
    Person p = new Person();
    V2Person v2 = new V2Person(p);
    CoveragePerson coveragePerson = new CoveragePerson();
    ChildList childList = new ChildList();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        p.setLastName("Scott");
        p.setFirstName("Michael");
        p.setPhone("4105551212");
        p.setAddress("78 3rd Street");
        p.setEmail("blahblah@blegh.com");

        v2.setLastName(p.getLastName());
        v2.setFirstName(p.getFirstName());
        v2.setAge(7);
        v2.setPhone(p.getPhone());
        v2.setEmail(p.getEmail());

        cA.setFirstName(v2.getFirstName());
        cA.setLastName(v2.getLastName());
        cA.setAge(v2.getAge());
        cA.setAddress(p.getAddress());

        coveragePerson.setFirstName("Michael");
        coveragePerson.setLastName("Scott");
        coveragePerson.setPhoneNumber(p.getPhone());
        coveragePerson.setAddress(p.getAddress());

        List<ChildAlert> cList = new ArrayList<>();
        List<CoveragePerson> aList = new ArrayList<>();

        cList.add(cA);
        aList.add(coveragePerson);
        childList.setChildList(cList);
        childList.setOtherMembers(aList);
    }

    @Test
    void testConstructor() {
        List<ChildAlert> childAlertList = childList.getChildList();
        List<CoveragePerson> otherMembers = childList.getOtherMembers();
        assertEquals(childAlertList, childList.getChildList());
        assertEquals(otherMembers, childList.getOtherMembers());
    }

    @Test
    void testConstructorEmpty() {
        assertTrue((new ChildList(new V2FireStation(new FireStation()))).getChildList().isEmpty());
    }



}

