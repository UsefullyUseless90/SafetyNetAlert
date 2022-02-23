package com.SafetyNetAlert.SafetyNet.ServiceTest;

import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import com.SafetyNetAlert.SafetyNet.service.MedicalRecordImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordImpl medicalRecordService;

    private MedicalRecord medicalRecord;
    private List<Medications> medications;
    private List<Allergies> allergies;


    @BeforeEach
    void setUp() {
        medications = new ArrayList<>();
        allergies = new HashSet<>();
        allergies1 = new Allergies();
        allergies1.setAllergies("nillacilan");
        medications1 = new Medications();
        medications1.setMedication("hydrapermazol:100mg");
        medications.add(medications1);
        allergies.add(allergies1);
        birthdate = LocalDate.of(2012,12,12);

        medicalRecord = new MedicalRecord();
        medicalRecord.setId(1);
        medicalRecord.setLastName("TestLastName");
        medicalRecord.setFirstName("TestFirstName");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        medicalRecord.setBirthdate(birthdate);
    }

    @Test
    public void findByIdTest() {
        when(medicalRecordRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(medicalRecord));

        Optional<MedicalRecord> medicalRecordsOptional = medicalRecordService.findById(1);
        MedicalRecord medicalRecord = new MedicalRecord();
        if(medicalRecordsOptional.isPresent()){
            medicalRecord = medicalRecordsOptional.get();
        }
        assertThat(medicalRecord.getFirstName()).isEqualTo("TestFirstName");

        verify(medicalRecordRepository, times(1)).findById(Mockito.anyInt());
    }

    @Test
    public void insertTest() {
        when(medicalRecordRepository.save(Mockito.any(MedicalRecord.class))).thenReturn(medicalRecord);

        MedicalRecord medicalRecordSave = medicalRecordService.insert(medicalRecord);

        assertThat(medicalRecordSave.getId()).isNotNull();
        assertThat(medicalRecordSave.getFirstName()).isEqualTo("TestFirstName");
    }

    @Test
    public void deleteTest() {
        ArgumentCaptor<MedicalRecord> argument = ArgumentCaptor.forClass(MedicalRecord.class);
        Optional<MedicalRecord> medicalRecordsOptional = medicalRecordRepository.findById(medicalRecord.getId());
        MedicalRecord medicalRecordsToDelete;
        if(medicalRecordsOptional.isPresent()) {
            medicalRecordsToDelete = medicalRecordsOptional.get();
            medicalRecordService.delete(medicalRecordsToDelete.getId());
            verify(medicalRecordRepository, times(1)).delete((MedicalRecord) argument.capture());
        }
    }

    @Test
    public void updateTest() {
        Optional<MedicalRecord> medicalRecordsOptional = medicalRecordRepository.findById(medicalRecord.getId());
        MedicalRecord medicalRecordToUpdate;
        if(medicalRecordsOptional.isPresent()) {
            medicalRecordToUpdate = medicalRecordsOptional.get();
            medicalRecordToUpdate.setFirstName("Update");
            when(medicalRecordService.update(medicalRecord.getId(), medicalRecordToUpdate)).thenReturn(medicalRecord);
            assertThat(medicalRecord.getFirstName()).isEqualTo("Update");
        }
    }

    @Test
    public void deleteByNameTest() {
        ArgumentCaptor<MedicalRecord> argumentCaptor = ArgumentCaptor.forClass(MedicalRecord.class);

        Optional<MedicalRecord> medicalRecordsOptional = medicalRecordRepository.findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
        MedicalRecord medicalRecordToDelete;
        if(medicalRecordsOptional.isPresent()) {
            medicalRecordToDelete = medicalRecordsOptional.get();
            medicalRecordService.deleteByName(medicalRecordToDelete.getFirstName(), medicalRecordToDelete.getLastName());
            verify(medicalRecordRepository, times(1)).delete(argumentCaptor.capture());
        }
    }

    @Test
    public void findAllTest() {
        List<MedicalRecord> medicalRecordsList = new ArrayList<MedicalRecord>();
        medicalRecordsList.add(medicalRecord);

        when(medicalRecordRepository.findAll()).thenReturn(medicalRecordsList);

        Optional<MedicalRecord> medicalRecordsList1 = medicalRecordService.getAll();
        MedicalRecord medicalRecords1 = medicalRecordsList1.iterator().next();

        assertThat(medicalRecords1.getFirstName()).isEqualTo("TestFirstName");

        verify(medicalRecordRepository, times(1)).findAll();
    }
}
