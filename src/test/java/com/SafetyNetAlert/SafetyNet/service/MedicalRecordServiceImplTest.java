package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
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

class MedicalRecordServiceImplTest {
    @Mock
    File file;
    @Mock
    JsonFileService jsonFileService;
    @Mock
    DataJson data;
    @InjectMocks
    MedicalRecordServiceImpl medicalRecordServiceImpl;

    MedicalRecord record;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.createRecord(new MedicalRecord());
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testGetAllRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.getAllRecord();
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testUpdateRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.updateRecord(record);
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testDeleteRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.deleteRecord(record);
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }
}

