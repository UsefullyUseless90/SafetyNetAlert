package com.SafetyNetAlert.SafetyNet.ControllerTest;

import com.SafetyNetAlert.SafetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class StationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;



    @Test
    public void testGetByStationNumber() throws Exception{

        mockMvc.perform(get("/flood/{stations}")).andExpect(status().isOk());

    }
    @Test
    public void testGetByStationSector() throws Exception{

        mockMvc.perform(get("/fireStation/{stationNumber}")).andExpect(status().isOk());

    }
    @Test
    public void testGetByStationPhoneSector() throws Exception{

        mockMvc.perform(get("/phoneAlert/{fireStation}")).andExpect(status().isOk());

    }
    @Test
    public void testGetByFireAddress() throws Exception{

        mockMvc.perform(get("/fire/{address}")).andExpect(status().isOk());

    }
}
