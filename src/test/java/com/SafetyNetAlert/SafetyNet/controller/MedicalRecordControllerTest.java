package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.service.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.mockito.Mockito.*;

class MedicalRecordControllerTest {
    @Mock
    MedicalRecordService medicalRecordService;
    @InjectMocks
    MedicalRecordController medicalRecordController;

    private static final MedicalRecord record = new MedicalRecord();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateRecord() throws Exception {
        record.setFirstName("Jim");
        record.setLastName("Halpert");
        //record.setBirthdate("04/12/85"); convert string to localDate!
        record.setAllergies(Collections.singletonList("100mg Aspirin"));
        record.setMedications(Collections.singletonList("SeaShells"));
        String content = (new ObjectMapper()).writeValueAsString(record);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllRecord() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicalRecord");
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateRecord() throws Exception {
        record.setFirstName("Jim");
        record.setLastName("Halpert");
        //record.setBirthdate("04/12/85");
        record.setAllergies(Collections.singletonList("100mg Aspirin"));
        record.setMedications(Collections.singletonList("SeaShells"));
        String content = (new ObjectMapper()).writeValueAsString(record);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDeleteRecord() throws Exception {
        record.setFirstName("Jim");
        record.setLastName("Halpert");
        //record.setBirthdate("04/12/85");
        record.setAllergies(Collections.singletonList("100mg Aspirin"));
        record.setMedications(Collections.singletonList("SeaShells"));
        String content = (new ObjectMapper()).writeValueAsString(record);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
