package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> createRecord(MedicalRecord record) throws IOException, JSONException;

    List<MedicalRecord> getAllRecord() throws JSONException, IOException;

    List<MedicalRecord> updateRecord(MedicalRecord record) throws IOException, JSONException;

    List<MedicalRecord> deleteRecord(MedicalRecord record) throws IOException;

}
