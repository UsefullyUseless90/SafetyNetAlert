package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private ModelMapper modelMapper;


    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    private Optional<MedicalRecord> getById(long id) {
        return null;
    }

    /**
     * Insert medical records.
     *
     * @param medicalRecords the medical records
     * @return the medical records
     */
    MedicalRecord insert(MedicalRecord medicalRecords) {
        return null;
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id) {

    }

    /**
     * Update medical records.
     *
     * @param id             the id
     * @param medicalRecords the medical records
     * @return the medical records
     */
    MedicalRecord update(Long id, MedicalRecord medicalRecords) {
        return null;
    }

    /**
     * Delete by name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public void deleteByName(String firstName, String lastName) {

    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public Optional<MedicalRecord> getAll() {
        return null;
    }
}
