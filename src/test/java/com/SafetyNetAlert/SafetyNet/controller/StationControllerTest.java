package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class StationControllerTest {
    @Mock
    FireStationService fireStationService;
    @InjectMocks
    StationController stationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateStation() throws Exception {
        // when(this.personService.getAllPerson()).thenReturn(new ArrayList<>());

        FireStation station = new FireStation();
        station.setAddress("42 Main St");
        station.setStation("2");
        String content = (new ObjectMapper()).writeValueAsString(station);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.stationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAllStation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/firestation");
        MockMvcBuilders.standaloneSetup(this.stationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateStation() throws Exception {
        FireStation station = new FireStation();
        station.setAddress("42 Main St");
        station.setStation("2");
        String content = (new ObjectMapper()).writeValueAsString(station);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.stationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDeleteStation() throws Exception {
        FireStation station = new FireStation();
        station.setAddress("42 Main St");
        station.setStation("2");
        String content = (new ObjectMapper()).writeValueAsString(station);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.stationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme