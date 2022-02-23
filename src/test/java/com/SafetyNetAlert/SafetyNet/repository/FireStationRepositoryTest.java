package com.SafetyNetAlert.SafetyNet.repository;

import com.SafetyNetAlert.SafetyNet.model.FireStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FireStationRepositoryTest {

    @Autowired
    private FireStationRepository underTest;

    private String address = "10 baker Street";
    private int stationNumber = 1;
    private long id = 1L;

    @Test
    void itShouldFindByStationSector() {
        //given
        FireStation fireStation = new FireStation(address);

        //when
        underTest.findByStationSector(address);
        //then
    }

    @Test
    void itShouldFindByStationNumber() {
    }
}