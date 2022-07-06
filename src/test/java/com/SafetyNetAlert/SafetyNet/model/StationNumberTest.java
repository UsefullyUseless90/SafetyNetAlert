package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StationNumberTest {

    @Test
    void testConstructor() {

        StationNumber actualStationNumber = new StationNumber(new V2FireStation(new FireStation()));
        assertEquals(0, actualStationNumber.getAdults());
        assertTrue(actualStationNumber.getPersonList().isEmpty());
        assertEquals(0, actualStationNumber.getChildren());
    }

}

