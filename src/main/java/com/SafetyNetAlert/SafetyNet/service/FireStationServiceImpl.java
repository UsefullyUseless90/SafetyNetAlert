package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.Person;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FireStationServiceImpl implements FireStationService{

    File file = new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\JsonDataSafetyNet.json");

    @Autowired
    private JsonFileService jsonFileService;

    /**
     * @param station
     * @return an updated list of all the Stations
     * @throws IOException
     * @throws JSONException
     */
    @Override
    public List<FireStation> createStation(FireStation station) throws IOException, JSONException {
        List<FireStation> stationList = this.getAllStation();
        station.setStation(station.getStation());
        station.setAddress(station.getAddress());
        stationList.add(station);

        jsonFileService.updateStations(stationList);// Writing in JSON file

        return this.getAllStation();
    }

    /**
     *
     * @return list of all stations
     * @throws IOException
     */
    public List<FireStation> getAllStation() throws IOException {
        List<FireStation> culvertStationList = jsonFileService.jsonReaderService().getFirestations();
        return culvertStationList;
    }

    /**
     *
     * @param station
     * @return an updated list of all stations
     * @throws IOException
     * @throws JSONException
     */
    @Override
    public List<FireStation> updateStation(FireStation station) throws IOException, JSONException {

        List<FireStation> stationList = this.getAllStation();// create a list and add to it the persons

        //Instantiate the loop that'll look in the list for any match
        for (int i = 0; i < stationList.size(); i++) {
           FireStation f = stationList.get(i);
            if (Objects.equals(f.getStation(), station.getStation()) && f.getAddress().equals(station.getAddress())) { // In case of any match the value is replaced by a new one
                stationList.set(i, station);
                break;
            }
        }
        this.jsonFileService.updateStations(stationList); // Writing in JSON file

        return stationList;
    }

    /**
     *
     * @param station
     * @return
     * @throws IOException
     */
    @Override
    public List<FireStation> deleteStation(FireStation station) throws IOException {
        List<FireStation> stationList = this.getAllStation();// create a list and add to it the stations

        //Instantiate the loop that'll look in the list for any match
        for (int i = 0; i < stationList.size(); i++) {
            FireStation f = stationList.get(i);
            if (Objects.equals(f.getStation(), station.getStation()) && f.getAddress().equals(station.getAddress())) {// In case of any match the value is deleted
                stationList.remove(i);
                break;
            }
        }
        this.jsonFileService.updateStations(stationList); // Writing in JSON file

        return stationList;
    }
}

