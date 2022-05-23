package com.SafetyNetAlert.SafetyNet.jsonfiles;

import com.SafetyNetAlert.SafetyNet.model.V2FireStation;
import com.SafetyNetAlert.SafetyNet.model.V2FireStationList;
import com.SafetyNetAlert.SafetyNet.model.V2Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class V2JsonFileService {

    private static File output = new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\output.json");
    V2Person v2Person;

    public void jsonCreateService(V2FireStationList v2FireStationList) throws IOException {

        JSONObject json = new JSONObject();
        List<V2FireStation> stationList = v2FireStationList.getStations();
        json.put("stationList", stationList);
        for (int i = 0; i < stationList.size(); i++) {
            for (int k = 0; k < stationList.get(i).getFamilyList().get(0).getPersonList().size(); k++) {
                LocalDate birthdate = stationList.get(i).getFamilyList().get(0).getPersonList().get(k).getBirthdate();
                String formattedBirthdate = birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
        }
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JSR310Module());
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                mapper.writerWithDefaultPrettyPrinter().writeValue(output, json);
                // until here all good !//
    }
}