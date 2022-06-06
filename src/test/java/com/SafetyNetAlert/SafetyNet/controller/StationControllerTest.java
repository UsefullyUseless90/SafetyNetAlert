package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.CoveragePerson;
import com.SafetyNetAlert.SafetyNet.model.StationNumber;
import com.SafetyNetAlert.SafetyNet.service.FireStationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StationController.class)
class StationControllerTest {

    private static final String jsonPost = "{\n" +
            "      \"address\": \"29 14th St\",\n" +
            "      \"station\": \"2\"\n" +
            "    }";
    private static final String jsonPut ="{\n" +
            "  \"id\": 60,\n" +
            "  \"address\": \"29 14th St\",\n" +
            "  \"station\": \"2\"\n" +
            "}";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FireStationService fireStationService;
    @InjectMocks
    StationController stationController;

@Test
public void testGetAllStations() throws Exception {
    mockMvc.perform(get("/firestation")).andExpect(status().isOk());
}
@Test
public void testCreateStation() throws Exception {
    final ResultActions result = mockMvc.perform(post("/firestation")
            .content(jsonPost)
            .contentType(MediaType.APPLICATION_JSON));
    result.andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.content()
                    .contentType(MediaType.APPLICATION_JSON));
}
@Test
public void testUpdateFireStation() throws Exception {
    mockMvc.perform(
            post("/firestation")
                    .content(jsonPost)
                    .contentType(MediaType.APPLICATION_JSON));
    final ResultActions result = mockMvc.perform(put("/firestation")
                    .content(jsonPut)
                    .contentType(MediaType.APPLICATION_JSON));
    result.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON));
}
    @Test
    public void testDeleteFireStation() throws Exception {
        mockMvc.perform(post("/firestation")
                        .content(jsonPost)
                        .contentType(MediaType.APPLICATION_JSON));
        final ResultActions result = mockMvc.perform(delete("/firestation")
                        .content(jsonPost)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }
    @Test
    public void testGetFireStations() throws Exception {
        final ResultActions result = mockMvc.perform(get("/firestation")
                                            .param("stationNumber", "3"));

        final MockHttpServletResponse response =
                result.andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse();

        StationNumber stationNumber = new StationNumber();

        CoveragePerson coveragePerson1 = new CoveragePerson();
        coveragePerson1.setFirstName("Andrew");
        coveragePerson1.setLastName("Cooper");
        coveragePerson1.setAddress("77th 2nd Street");
        coveragePerson1.setPhoneNumber("555-689-328");

        CoveragePerson coveragePerson2 = new CoveragePerson();
        coveragePerson2.setFirstName("Xander");
        coveragePerson2.setLastName("Cooper");
        coveragePerson2.setAddress("77th 2nd Street");
        coveragePerson2.setPhoneNumber("555-689-328");

        List<CoveragePerson> coveragePersonList = new ArrayList<>();
        coveragePersonList.add(coveragePerson1);
        coveragePersonList.add(coveragePerson2);

        stationNumber.setPersonList(coveragePersonList);
        stationNumber.setAdults(1);
        stationNumber.setChildren(1);

        Assertions.assertTrue(stationNumber.getAdults() > 0);
        Assertions.assertTrue(stationNumber.getChildren() > 0);
        Assertions.assertTrue(stationNumber.getPersonList().size() > 0);

}

}