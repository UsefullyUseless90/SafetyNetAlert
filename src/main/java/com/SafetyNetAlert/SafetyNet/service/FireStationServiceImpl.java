package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.StationNumber;
import com.SafetyNetAlert.SafetyNet.model.V2FireStation;
import com.SafetyNetAlert.SafetyNet.model.V2FireStationList;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FireStationServiceImpl implements FireStationService {

    @Autowired
    private JsonFileService jsonFileService;
    private StationNumber number;

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
     * @return list of all stations
     * @throws IOException
     */
    public List<FireStation> getAllStation() throws IOException {
        List<FireStation> culvertStationList = jsonFileService.jsonReaderService().getFirestations();
        return culvertStationList;
    }

    /**
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
     * @param station
     * @return
     * @throws IOException
     */
    @Override
    public List<FireStation> deleteStation(FireStation station) throws IOException {
        List<FireStation> stationList = this.getAllStation(); // create a list and add to it the stations

        //Instantiate the loop that'll look in the list for any match
        for (int i = 0; i < stationList.size(); i++) {
            FireStation f = stationList.get(i);
            if (f.getStation().equals(station.getStation()) && f.getAddress().equals(station.getAddress())) { // In case of any match the value is deleted
                stationList.remove(i);
                break;
            }
        }
        this.jsonFileService.updateStations(stationList); // Writing in JSON file

        return stationList;
    }

    /**
     * @param stationNumber
     * @return
     * @throws IOException
     */

    @Override
    public List<StationNumber> filteredData(String stationNumber) throws IOException {
        V2FireStationList v2FireStationList = new V2FireStationList(jsonFileService.jsonReaderService());
        List<StationNumber> numbers = new ArrayList<>();
        for (V2FireStation v2FireStation : v2FireStationList.getStations()) {
            if (v2FireStation.getId().equals(stationNumber)) {
                StationNumber answerStationNumber = new StationNumber(v2FireStation);
                answerStationNumber.filteredPeople(v2FireStation);
                answerStationNumber.getPersonList();
                answerStationNumber.getAdults();
                answerStationNumber.getChildren();
                numbers.add(answerStationNumber);
            }
        }
        return numbers;

    }
}