package com.SafetyNetAlert.SafetyNet.repository;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

    /**
     *
     * @return
     */
    List<MedicalRecord>findByMedicalRecord();

    List<MedicalRecord>findByAge();
    /**
     * Find by first name and last name optional.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the optional
     */
    Optional<MedicalRecord> findByFirstNameAndLastName(
            String firstName, String lastName);

    /**
     * Find birth date by first name and last name local date.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the local date
     */
    LocalDate findBirthDateByFirstNameAndLastName(
            String firstName, String lastName);


    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<MedicalRecord> findById(long id);

    /**
     * Delete.
     *
     * @param id the id
     */

    /**
     * Find all list.
     *
     * @return the list
     */
    List<MedicalRecord> findAll();

}
