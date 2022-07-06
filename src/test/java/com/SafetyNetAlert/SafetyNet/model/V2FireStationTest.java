package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class V2FireStationTest {

    @Test
    void testConstructor() {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        V2FireStation actualV2FireStation = new V2FireStation(fireStation);
        List<V2Family> familyList = actualV2FireStation.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", actualV2FireStation.getId());
        V2Family getResult = familyList.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertTrue(getResult.getPersonList().isEmpty());
        assertEquals(0, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
    }


    @Test
    void testAddNewFamily2() {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        V2FireStation v2FireStation = new V2FireStation(fireStation);
        v2FireStation.addNewFamily("42 Main St");
        assertEquals(1, v2FireStation.getFamilyList().size());
    }


    @Test
    void testAddNewFamily3() {
        V2FireStation v2FireStation = new V2FireStation(new FireStation());
        v2FireStation.setFamilyList(new ArrayList<>());
        v2FireStation.addNewFamily("42 Main St");
        List<V2Family> familyList = v2FireStation.getFamilyList();
        assertEquals(1, familyList.size());
        V2Family getResult = familyList.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertTrue(getResult.getPersonList().isEmpty());
        assertEquals(0, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
    }
}

