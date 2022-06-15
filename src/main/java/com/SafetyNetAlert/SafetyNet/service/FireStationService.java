package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.StationNumber;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface FireStationService {

    List<FireStation> createStation(FireStation station) throws IOException, JSONException;

    List<FireStation> getAllStation() throws JSONException, IOException;

    List<FireStation> updateStation(FireStation station) throws IOException, JSONException;

    List<FireStation> deleteStation(FireStation station) throws IOException;

    StationNumber filteredData(String stationNumber) throws IOException;

}
