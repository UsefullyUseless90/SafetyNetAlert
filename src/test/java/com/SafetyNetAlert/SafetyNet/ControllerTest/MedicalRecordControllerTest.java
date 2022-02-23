package com.SafetyNetAlert.SafetyNet.ControllerTest;

import com.SafetyNetAlert.SafetyNet.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void findAllTest() throws Exception{

        mockMvc.perform(get("/medicalRecord")).andExpect(status().isOk());

    }
    @Test
    public void addMedicalRecordTest() throws Exception{
        Person person = new Person(1,"Andrew", "Thompson","04/02/98","aznol:60mg","none");
        String json = mapper.writeValueAsString(person);


        mockMvc.perform(get("/"))

    }


}
