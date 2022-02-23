package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalRecordImpl extends MedicalRecordService {

    private final MedicalRecordRepository repository;

    /**
     * Instantiates a new Medical record service.
     *
     * @param repository1 the repository
     */
    public MedicalRecordImpl(final MedicalRecordRepository repository1) {
        this.repository = repository1;
    }

    /**
     * FindById.
     *
     * @param id the id
     * @return optional of medicalrecords
     */
    @Override
    public Optional<MedicalRecord> findById(final long id) {
        return repository.findById(id);
    }

    /**
     * Insert.
     *
     * @param medicalRecord the medical records
     * @return medicalrecord
     */
    @Override
    public MedicalRecord insert(final MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    public void delete(final long id) {
        Optional<MedicalRecord> medicalRecord = this.findById(id);
        medicalRecord.ifPresent(m -> repository.delete(m));
    }

    /**
     * Update.
     *
     * @param id             the id
     * @param medicalRecords the medical records
     * @return medicalRecord update
     */
    @Override
    public MedicalRecord update(final long id,
                                final MedicalRecord medicalRecords) {
        Optional<MedicalRecord> mr1 = this.findById(id);
        if (mr1.isPresent()) {
            MedicalRecord medicalRecordsToUpdate = mr1.get();
            medicalRecordsToUpdate.setBirthdate(medicalRecords.getBirthdate());
            medicalRecordsToUpdate.setMedications(
                    medicalRecords.getMedications());
            medicalRecordsToUpdate.setAllergies(medicalRecords.getAllergies());
            medicalRecordsToUpdate.setFirstName(medicalRecords.getFirstName());
            medicalRecordsToUpdate.setLastName(medicalRecords.getLastName());

            return repository.save(medicalRecordsToUpdate);
        } else {
            return null;
        }

    }
}
