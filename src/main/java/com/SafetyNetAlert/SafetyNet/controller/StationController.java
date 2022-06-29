package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.service.FireStationService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/firestation")
public class StationController {

    @Autowired
    FireStationService fireStationService;

    /**
     * Create / Add new station
     *
     * @param station
     * @return creation
     * @throws IOException
     * @throws JSONException
     */

    @PostMapping
    public ResponseEntity<FireStation> createStation(@RequestBody FireStation station) throws IOException, JSONException {
        fireStationService.createStation(station);
        ResponseEntity<FireStation> creation = ResponseEntity.status(HttpStatus.CREATED).body(station);
        return creation;
    }


    /**
     * Read / Get all stations
     *
     * @return results
     * @throws JSONException
     * @throws IOException
     */

    @RequestMapping(value ="")
    public ResponseEntity<List<FireStation>> getAllStation() throws JSONException, IOException {
        List<FireStation> stationList = fireStationService.getAllStation();
        ResponseEntity<List<FireStation>> result = ResponseEntity.status(HttpStatus.OK).body(stationList);
        return result;

    }

    /**
     * Update an existing station
     *
     * @param station
     * @throws IOException
     * @throws JSONException
     * @return
     */

    @PutMapping
    public ResponseEntity<?> updateStation(@RequestBody FireStation station) throws IOException, JSONException {
        List<FireStation> updatedStation = fireStationService.updateStation(station);
        return new ResponseEntity<>(updatedStation, HttpStatus.OK);
    }

    /**
     * Delete a station from the record
     * @param station
     * @return
     * @throws IOException
     */

    @DeleteMapping
    public ResponseEntity<String> deleteStation(@RequestBody FireStation station) throws IOException {
        fireStationService.deleteStation(station);

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted!");
    }

    /**
     *
     * @param stationNumber
     * @return
     * @throws IOException
     */

    @RequestMapping(value ="", params = "stationNumber")
    public ResponseEntity<?>filteredData(@RequestParam String stationNumber) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fireStationService.filteredData(stationNumber));
    }

}
