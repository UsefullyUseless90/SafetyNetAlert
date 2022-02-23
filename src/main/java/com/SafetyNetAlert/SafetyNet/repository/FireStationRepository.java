package com.SafetyNetAlert.SafetyNet.repository;

import com.SafetyNetAlert.SafetyNet.model.FireStation;

import com.SafetyNetAlert.SafetyNet.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationRepository extends CrudRepository<FireStation, Long> {

    /**
     * @param stationNumber the station number
     * @return the list
     */
    List<FireStation>findByStationNumber(long stationNumber);
    /**
     *
     * @param address station's address
     * @return the station's infos
     */
    List<FireStation>findByStationSector(String address);

}
