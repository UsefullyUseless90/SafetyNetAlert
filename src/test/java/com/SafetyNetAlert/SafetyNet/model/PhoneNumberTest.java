package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PhoneNumberTest {

    @Test
    void testConstructor() {
        assertTrue((new PhoneNumber(new V2FireStation(new FireStation()))).getPhoneNumber().isEmpty());
    }
}

