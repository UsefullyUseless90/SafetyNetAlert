package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MedicalRecordServiceImpl implements MedicalRecordService {
    File file = new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\JsonDataSafetyNet.json");
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

        jsonFileService.updateRecords(recordList); // Writing in JSON file

        return this.getAllRecord();
    }

    /**
     *
     * @return list of all stations
     * @throws IOException
     */
    public List<MedicalRecord> getAllRecord() throws IOException {

        return jsonFileService.jsonReaderService().getMedicalrecords();// // Reading the JSON file
    }

    /**
     *
     * @param record
     * @return an updated list of records
     * @throws IOException
     */
    @Override
    public List<MedicalRecord> updateRecord(MedicalRecord record) throws IOException {

        List<MedicalRecord> recordList = this.getAllRecord();// create the list of all the persons

        //Instantiate the loop that'll look in the list for any match
        for (int i = 0; i < recordList.size(); i++) {
            MedicalRecord mr = recordList.get(i);
            if (mr.getLastName().equals(record.getLastName()) && mr.getFirstName().equals(record.getFirstName())) { // In case of any match the value is replaced by a new one
                recordList.set(i, record);
                break;
            }
        }
        this.jsonFileService.updateRecords(recordList); // Writing in JSON file

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
        List<MedicalRecord> recordList = this.getAllRecord();// create a list and add to it the persons

        for (int i = 0; i < recordList.size(); i++) {
            MedicalRecord mr = recordList.get(i);
            if (mr.getLastName().equals(record.getLastName()) && mr.getFirstName().equals(record.getFirstName())) { // In case of any match the value is deleted
                recordList.remove(i);
                break;
            }
        }
        this.jsonFileService.updateRecords(recordList); // Writing in JSON file

        return recordList;
    }
}

