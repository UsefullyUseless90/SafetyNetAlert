package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.apache.logging.log4j.Logger;
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

    private Logger logger = LogManager.getLogger(MedicalRecordController.class);

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
        logger.info("Record creation please wait...");
        medicalRecordService.createRecord(record);
        ResponseEntity<MedicalRecord> creation = ResponseEntity.status(HttpStatus.CREATED).body(record);
        logger.info("Record :" + creation);
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
        logger.info("Records checking, please wait...");
        List<MedicalRecord> recordList = medicalRecordService.getAllRecord();
        ResponseEntity<List<MedicalRecord>> result = ResponseEntity.status(HttpStatus.OK).body(recordList);
        logger.info("Records list:" + result);
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
        logger.info("Record checking,seeking for matches, please wait...");
        List<MedicalRecord> updatedRecord = medicalRecordService.updateRecord(record);
        logger.info("Record Updated!" + updatedRecord);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }


    /**
     * Delete a record
     * @param record
     * @return
     * @throws IOException
     */

    @DeleteMapping
    public ResponseEntity<?> deleteRecord(@RequestBody MedicalRecord record) throws IOException {
        logger.info("Record checking,seeking for matches, please wait...");
        medicalRecordService.deleteRecord(record);
        logger.info("Successfully Deleted!");
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }


}


