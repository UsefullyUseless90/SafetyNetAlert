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

    /**
     * Fonction permettant de lire le fichier JSON et de le transformer en objet JAVA avec Jackson
     *
     * @return DataJson, classe JAVA représentant l'intégralité du fichier JSON
     * @throws IOException
     */
    public DataJson jsonReaderService() throws IOException {
        List<String> allLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        StringBuilder myLine = new StringBuilder();
        for (String line : allLines) {
            myLine.append(line);
        }


        ObjectMapper mapper = new ObjectMapper();
        DataJson obj = mapper.readValue(myLine.toString(), DataJson.class);
        return obj;
    }

    /**
     * Fonction permettant de convertir la classe DataJson en texte dans le fichier JSON
     * @param dataJson
     * @throws IOException
     */
    public void jsonWriterService(DataJson dataJson) throws IOException {

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

