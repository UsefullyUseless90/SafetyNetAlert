package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class FireStationServiceImplTest {
    @Mock
    File file;
    @Mock
    JsonFileService jsonFileService;
    @Mock
    DataJson data;
    @Mock
    FireStation station;
    @InjectMocks
    FireStationServiceImpl fireStationServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStation() throws IOException, JSONException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.createStation(station);
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }

    @Test
    void testGetAllStation() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.getAllStation();
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }

    @Test
    void testUpdateStation() throws IOException, JSONException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.updateStation(new FireStation());
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }

    @Test
    void testDeleteStation() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.deleteStation(new FireStation());
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }
}
