package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class V2FireStationListTest {

    @Test
    void testConstructor() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        assertEquals(fireStationList, (new V2FireStationList(dataJson)).getStations());
    }

    @Test
    void testConstructor2() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(medicalRecordList, getResult1.getPersonList());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }
}

