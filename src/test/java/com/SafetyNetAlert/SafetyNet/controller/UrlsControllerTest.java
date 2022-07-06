package com.SafetyNetAlert.SafetyNet.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.SafetyNetAlert.SafetyNet.model.ChildList;
import com.SafetyNetAlert.SafetyNet.model.PhoneNumber;
import com.SafetyNetAlert.SafetyNet.service.ServiceUrls;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UrlsController.class})
@ExtendWith(SpringExtension.class)
class UrlsControllerTest {
    @MockBean
    private ServiceUrls serviceUrls;

    @Autowired
    private UrlsController urlsController;

    @Test
    void testChildAlert() throws Exception {
        when(this.serviceUrls.childAlert((String) any())).thenReturn(new ChildList());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert").param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"childList\":null,\"otherMembers\":null}"));
    }


    @Test
    void testChildAlert2() throws Exception {
        when(this.serviceUrls.childAlert((String) any())).thenReturn(new ChildList());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/childAlert");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"childList\":null,\"otherMembers\":null}"));
    }

    @Test
    void testPhoneNumber2() throws Exception {
        when(this.serviceUrls.phoneNumber((String) any())).thenReturn(new PhoneNumber());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/phoneAlert")
                .param("firestation", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"phoneNumber\":null}"));
    }

    @Test
    void testPhoneNumber3() throws Exception {
        when(this.serviceUrls.phoneNumber((String) any())).thenReturn(new PhoneNumber());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/phoneAlert");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("firestation", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"phoneNumber\":null}"));
    }

    @Test
    void testFireAddress() throws Exception {
        when(this.serviceUrls.fireAddress((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire").param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFireAddress2() throws Exception {
        when(this.serviceUrls.fireAddress((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/fire");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testPersonInfo2() throws Exception {
        when(this.serviceUrls.personInfo((String) any(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/personInfo");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("firstName", "foo").param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testCommunityEmail() throws Exception {
        when(this.serviceUrls.communityEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail").param("city", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testCommunityEmail2() throws Exception {
        when(this.serviceUrls.communityEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/communityEmail");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("city", "foo");
        MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFloodStation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flood/stations");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testPhoneNumber() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert")
                .param("firestationNumber", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

