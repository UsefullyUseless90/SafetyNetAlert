package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FireStationTest {

    @Test
    void testConstructor() {

        FireStation actualFireStation = new FireStation();
        actualFireStation.setAddress("42 Main St");
        actualFireStation.setStation("Station");
        assertEquals("42 Main St", actualFireStation.getAddress());
        assertEquals("Station", actualFireStation.getStation());
    }
}

