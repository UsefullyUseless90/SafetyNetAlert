package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.service.MedicalRecordService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    /**
     * Create / Add new record
     *
     * @param record
     * @return creation
     * @throws IOException
     * @throws JSONException
     */

    @PostMapping
    public ResponseEntity<MedicalRecord> createRecord(@RequestBody MedicalRecord record) throws IOException, JSONException {
        medicalRecordService.createRecord(record);
        ResponseEntity<MedicalRecord> creation = ResponseEntity.status(HttpStatus.CREATED).body(record);
        return creation;
    }


    /**
     * Read / Get all records
     *
     * @return results
     * @throws JSONException
     * @throws IOException
     */

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllRecord() throws JSONException, IOException {
        List<MedicalRecord> recordList = medicalRecordService.getAllRecord();
        ResponseEntity<List<MedicalRecord>> result = ResponseEntity.status(HttpStatus.OK).body(recordList);
        return result;

    }

    /**
     * Update an existing record
     *
     * @param record
     * @throws IOException
     * @throws JSONException
     * @return
     */

    @PutMapping
    public ResponseEntity<?> updateRecord(@RequestBody MedicalRecord record) throws IOException, JSONException {
        List<MedicalRecord> updatedRecord = medicalRecordService.updateRecord(record);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }


    /**
     * Delete a record
     * @param record
     * @return
     * @throws IOException
     */

    @DeleteMapping
    public ResponseEntity<String> deleteRecord(@RequestBody MedicalRecord record) throws IOException {
        medicalRecordService.deleteRecord(record);

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted!");
    }


}


