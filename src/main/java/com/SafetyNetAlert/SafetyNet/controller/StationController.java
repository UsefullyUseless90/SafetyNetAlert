package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.service.FireStationService;
import org.json.JSONException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private Logger logger = LogManager.getLogger(StationController.class);
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
        logger.info("Creating Station please wait...");
        fireStationService.createStation(station);
        ResponseEntity<FireStation> creation = ResponseEntity.status(HttpStatus.CREATED).body(station);
        logger.info("Station Created:" + creation);
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
        logger.info("Checking, please wait...");
        List<FireStation> stationList = fireStationService.getAllStation();
        ResponseEntity<List<FireStation>> result = ResponseEntity.status(HttpStatus.OK).body(stationList);
        logger.info("Station list:" + result);
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
        logger.info("Checking, please wait...");
        List<FireStation> updatedStation = fireStationService.updateStation(station);
        logger.info("Station Updated:" + updatedStation);
        return new ResponseEntity<>(updatedStation, HttpStatus.OK);
    }

    /**
     * Delete a station from the record
     * @param station
     * @return
     * @throws IOException
     */

    @DeleteMapping
    public ResponseEntity<?> deleteStation(@RequestBody FireStation station) throws IOException {
        logger.info("Checking, please wait...");
        fireStationService.deleteStation(station);
        logger.info("Successfully Deleted!");
        return ResponseEntity.status(HttpStatus.OK).body(station);
    }

    /**
     *
     * @param stationNumber
     * @return
     * @throws IOException
     */

    @RequestMapping(value ="", params = "stationNumber")
    public ResponseEntity<?>filteredData(@RequestParam String stationNumber) throws IOException {
        logger.info("Checking, please wait...");
        logger.info("URL n°1 StationNumber: " + fireStationService.filteredData(stationNumber));
        return ResponseEntity.status(HttpStatus.OK).body(fireStationService.filteredData(stationNumber));
    }

}
