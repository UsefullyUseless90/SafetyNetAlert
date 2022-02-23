package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

        @Autowired
        MedicalRecord medicalRecord;
        @Autowired
        MedicalRecordRepository medicalRecordRepository;

        List<MedicalRecord>getByMedicalRecord(){return medicalRecordRepository.findByMedicalRecord();}
        List<MedicalRecord>getMedicalRecordByAge(){return medicalRecordRepository.findByAge();}

}

