package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FloodStation {
    private List<FireAddress> fireAddressList;
    private String stationID;

    public FloodStation(DataJson dataJson, V2FireStation v2) {
        this.fireAddressList = new ArrayList<FireAddress>();
        this.stationID = v2.getId();

        for(FireStation f : dataJson.getFirestations()){
            for(FireAddress fireAddress : fireAddressList){
                if(f.getAddress().equals(fireAddress.getAddress())){
                    fireAddressList.add(fireAddress);
                }
            }
        }
    }
}
