package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.Person;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class V2Experiment {

    private JsonFileService jsonFileService;
    private DataJson json;

    public List<?> EmailCommunity() throws IOException {
        HashSet<List<Person>> hs = new HashSet<>();
        List<Person> reader = jsonFileService.jsonReaderService().getPersons();
        while(reader != null){
            hs.add(reader);
        }
        return (List<?>) hs;
    }





}
