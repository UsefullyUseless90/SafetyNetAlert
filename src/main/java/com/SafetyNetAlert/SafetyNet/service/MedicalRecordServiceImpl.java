package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private JsonFileService jsonFileService;


    /**
     * @param record
     * @return an updated list of all the records
     * @throws IOException
     */
    @Override
    public List<MedicalRecord> createRecord(MedicalRecord record) throws IOException {
        List<MedicalRecord> recordList = this.getAllRecord();
        record.setFirstName(record.getFirstName());
        record.setLastName(record.getLastName());
        record.setBirthdate(record.getBirthdate());
        record.setMedications(record.getMedications());
        record.setAllergies(record.getAllergies());
        recordList.add(record);

        // Writing in JSON file
        jsonFileService.updateRecords(recordList);

        return this.getAllRecord();
    }

    /**
     *
     * @return list of all stations
     * @throws IOException
     */

    public List<MedicalRecord> getAllRecord() throws IOException {
        // Reading the JSON file.
        return jsonFileService.jsonReaderService().getMedicalrecords();
    }

    /**
     *
     * @param record
     * @return an updated list of records
     * @throws IOException
     */
    @Override
    public List<MedicalRecord> updateRecord(MedicalRecord record) throws IOException {

        // Creates the list of all the persons.
        List<MedicalRecord> recordList = this.getAllRecord();

        //Instantiates the loop that'll look in the list for any match
        for (int i = 0; i < recordList.size(); i++) {
            MedicalRecord mr = recordList.get(i);
            // In case of any match the value is replaced by a new one.
            if (mr.getLastName().equals(record.getLastName()) && mr.getFirstName().equals(record.getFirstName())) {
                recordList.set(i, record);
                break;
            }
        }
        // Writing in JSON file
        this.jsonFileService.updateRecords(recordList);

        return recordList;
    }

    /**
     *
     * @param record
     * @return a list of records without the one that was deleted
     * @throws IOException
     */
    @Override
    public List<MedicalRecord> deleteRecord(MedicalRecord record) throws IOException {

        // Creates a list and add to it the persons
        List<MedicalRecord> recordList = this.getAllRecord();

        for (int i = 0; i < recordList.size(); i++) {
            MedicalRecord mr = recordList.get(i);
            // In case of any match the value is deleted
            if (mr.getLastName().equals(record.getLastName()) && mr.getFirstName().equals(record.getFirstName())) {
                recordList.remove(i);
                break;
            }
        }
        // Writing in JSON file
        this.jsonFileService.updateRecords(recordList);

        return recordList;
    }
}

