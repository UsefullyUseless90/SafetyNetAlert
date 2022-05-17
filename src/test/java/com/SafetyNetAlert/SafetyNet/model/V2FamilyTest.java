package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class V2FamilyTest {
    /**
     * Method under test: default or parameterless constructor of {@link V2Family}
     */
    @Test
    void testConstructor() {
        V2Family actualV2Family = new V2Family("42 Main St");
        assertEquals("42 Main St", actualV2Family.getAddress());
        assertTrue(actualV2Family.getPersonList().isEmpty());
        assertEquals(0, actualV2Family.getHouseHoldMembers());
        assertEquals(0, actualV2Family.getCounter());
        assertEquals(0, actualV2Family.getChildren());
        assertEquals(0, actualV2Family.getAdults());
    }

    /**
     * Method under test: {@link V2Family#addPerson(Person)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPerson() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.time.Period.between(Period.java:387)
        //       at com.SafetyNetAlert.SafetyNet.model.V2Family.calculateAge(V2Family.java:60)
        //       at com.SafetyNetAlert.SafetyNet.model.V2Family.addPerson(V2Family.java:52)
        //   In order to prevent addPerson(Person)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addPerson(Person).
        //   See https://diff.blue/R013 to resolve this issue.

        V2Family v2Family = new V2Family("42 Main St");

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        v2Family.addPerson(person);
    }

    /**
     * Method under test: {@link V2Family#addPerson(Person)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPerson2() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.time.Period.between(Period.java:387)
        //       at com.SafetyNetAlert.SafetyNet.model.V2Family.calculateAge(V2Family.java:60)
        //       at com.SafetyNetAlert.SafetyNet.model.V2Family.addPerson(V2Family.java:52)
        //   In order to prevent addPerson(Person)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addPerson(Person).
        //   See https://diff.blue/R013 to resolve this issue.

        V2Family v2Family = new V2Family("42 Main St");
        v2Family.setCounter(3);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        v2Family.addPerson(person);
    }

    /**
     * Method under test: {@link V2Family#addPerson(Person)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPerson3() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.time.Period.between(Period.java:387)
        //       at com.SafetyNetAlert.SafetyNet.model.V2Family.calculateAge(V2Family.java:60)
        //       at com.SafetyNetAlert.SafetyNet.model.V2Family.addPerson(V2Family.java:52)
        //   In order to prevent addPerson(Person)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addPerson(Person).
        //   See https://diff.blue/R013 to resolve this issue.

        V2Family v2Family = new V2Family("42 Main St");

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        v2Family.addPerson(person);
    }

    /**
     * Method under test: {@link V2Family#calculateAge(Person)}
     */
    @Test
    void testCalculateAge() {
        V2Family v2Family = new V2Family("42 Main St");

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        v2Family.calculateAge(person);
        assertEquals("42 Main St", person.getAddress());
        assertEquals("21654", person.getZip());
        assertEquals("4105551212", person.getPhone());
        assertEquals("Doe", person.getLastName());
        assertEquals("Jane", person.getFirstName());
        assertEquals("jane.doe@example.org", person.getEmail());
        assertEquals("Oxford", person.getCity());
        assertEquals("42 Main St", v2Family.getAddress());
        assertTrue(v2Family.getPersonList().isEmpty());
        assertEquals(0, v2Family.getHouseHoldMembers());
        assertEquals(0, v2Family.getCounter());
        assertEquals(0, v2Family.getChildren());
        assertEquals(0, v2Family.getAdults());
    }
}

