package com.SafetyNetAlert.SafetyNet.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.SafetyNetAlert.SafetyNet.model.ChildList;
import com.SafetyNetAlert.SafetyNet.model.PhoneNumber;
import com.SafetyNetAlert.SafetyNet.service.ServiceUrls;

import java.util.ArrayList;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ComponentScan
@ContextConfiguration(classes = {UrlsController.class})
@ExtendWith(SpringExtension.class)
class UrlsControllerTest {
    @MockBean
    private ServiceUrls serviceUrls;

    @MockBean
    private UrlsController urlsController;

    @Test
    void testChildAlertEmpty() throws Exception {
        when(this.serviceUrls.childAlert((String) any())).thenReturn(new ChildList());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert").param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testChildAlert() throws Exception {
        when(this.serviceUrls.childAlert((String) any())).thenReturn(new ChildList());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/childAlert");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testChildAlertBadRequest() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
            .build()
            .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
}

    @Test
    void testFireAddress() throws Exception {
        when(this.serviceUrls.fireAddress((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire").param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void testFireAddressEmpty() throws Exception {
        when(this.serviceUrls.fireAddress((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/fire");
        getResult.contentType("fire");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFireAddressBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    void testPersonInfo() throws Exception {
        when(this.serviceUrls.personInfo((String) any(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo")
                .param("firstName", "foo")
                .param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testPersonInfoEmpty() throws Exception {
        when(this.serviceUrls.personInfo((String) any(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/personInfo");
        getResult.contentType("person");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("firstName", "foo").param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testPersonInfoBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testCommunityEmailEmpty() throws Exception {
        when(this.serviceUrls.communityEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail").param("city", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCommunityEmail() throws Exception {
        when(this.serviceUrls.communityEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/communityEmail");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("city", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCommunityEmailBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testFloodStationBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flood/stations");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testPhoneNumber() throws Exception {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber(new ArrayList<>());
        when(this.serviceUrls.phoneNumber((String) any())).thenReturn(phoneNumber);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/phoneAlert")
                .param("firestation", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testPhoneNumberBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert")
                .param("firestationNumber", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

