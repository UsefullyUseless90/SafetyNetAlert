package com.SafetyNetAlert.SafetyNet.jsonfiles;

import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Service
@Getter
@Setter
public class JsonFileService {

    private static File file = new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\JsonDataSafetyNet.json");
    //private static String key = "firstName" + "lastName";

    /**
     * Fonction permettant de lire le fichier JSON et de le transformer en objet JAVA avec Jackson
     *
     * @return DataJson, classe JAVA représentant l'intégralité du fichier JSON
     * @throws IOException
     */
    public DataJson jsonReaderService() throws IOException {
        File file = new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\JsonDataSafetyNet.json");
        List<String> allLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        StringBuilder myLine = new StringBuilder();
        for (String line : allLines) {
            myLine.append(line);
        }


        ObjectMapper mapper = new ObjectMapper();
        DataJson obj = mapper.readValue(myLine.toString(), DataJson.class);
        return obj;
    }
    // new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\JsonDataSafetyNet.json")

    // Général gestion d'un fichier
    // 1 - Lecture du fichier ==>> DataJson
    // 2 _ MAJ de l'objet souhaité ==>> Dans lDataJson, boucler sur les objects afin d'identifier celui qu'il faut meetre à jours
    // 3 - Ecriture du nouvel objet (efface le fichier complet puis réécriture complète)

    /**
     * Fonction permettant de convertir la classe DataJson en texte dans le fichier JSON
     * @param dataJson
     * @throws IOException
     */
    public void jsonWriterService(DataJson dataJson) throws IOException {

        // Comment convertir ce dataJson en text ?

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, dataJson);
    }

    public void updatePersons(List<Person> personList) throws IOException {
        DataJson dataJson = this.jsonReaderService();
        dataJson.setPersons(personList);
        this.jsonWriterService(dataJson);
    }
    public void updateStations(List<FireStation> stationList) throws IOException {
        DataJson dataJson = this.jsonReaderService();
        dataJson.setFirestations(stationList);
        this.jsonWriterService(dataJson);
    }
    public void updateRecords(List<MedicalRecord> recordList) throws IOException {
        DataJson dataJson = this.jsonReaderService();
        dataJson.setMedicalrecords(recordList);
        this.jsonWriterService(dataJson);
    }
}


    // Methode à avoir dans la couche de Service pour le JSON

    // lirejSOn -->> DtaaJson


    // updateJson -->> MAJ le Json complet //lireJson() -->> Parser l'ensemble Json dans le fichier

    // updatePersonsJson(List<Person> personList)
    // updateFireStationJson(List<FireStation> firestationList)
    // updateMedicalRecords(List<MedicalRecords> medicalRecordList)


