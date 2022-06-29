package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.service.ServiceUrls;
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



    /**
     * Method under test: {@link UrlsController#phoneNumber(String)}
     */
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

