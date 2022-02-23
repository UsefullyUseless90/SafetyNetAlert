package com.SafetyNetAlert.SafetyNet.repository;

import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
        /**
         *
         * @param city people's city
         * @return list of people's email from a city
         */
        List<Person> findByCity(String city);

        /**
         *
         *  @param firstName people's firstName
         *  @param lastName people's lastName
         *  @return list of people's info
         *
         */
        List<Person> findByFirstNameAndLastName(String firstName, String lastName);// personInfo

        /**
         *
         * @param address people's address
         * @return list of people's age living at a specific address
         */
        List<Person>findByAddress(String address);

        /**
         * Find all by address list.
         *
         * @param address the address
         * @return the list
         */
        List<Person> findAllByAddress(String address);

        /**
         * @param address the address
         *
         */
        List<Person>findByFireAddress(String address);

        /**
         *
         * @param address people's address / station's address
         * @param phone people's phone info / so messages can get send
         * @return people's phone by sector station covered
         */
        List<Person>findByPhoneSector(String address, String phone);






}
