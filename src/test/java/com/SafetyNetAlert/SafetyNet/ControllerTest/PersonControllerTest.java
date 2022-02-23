package com.SafetyNetAlert.SafetyNet.ControllerTest;

import com.SafetyNetAlert.SafetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    PersonService personService;


    @Test
    public void testGetByFirstNameAndLastName() throws Exception{

        mockMvc.perform(get("/personInfo/{firstName}/{lastName}")).andExpect(status().isOk());
    }
    @Test
    public void testGetAllCommunityEmail() throws Exception{

        mockMvc.perform(get("/communityEmail/{city}")).andExpect(status().isOk());

    }

    @Test
    public void testGetPersonAgedEighteenAndUnder() throws Exception{

        mockMvc.perform(get("/childAlert/{address}")).andExpect(status().isOk());

    }

}

}
