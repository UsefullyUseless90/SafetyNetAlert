package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.*;

import java.io.IOException;
import java.util.List;

public interface ServiceUrls{

ChildList childAlert(String address) throws IOException;

PhoneNumber phoneNumber(String stationNumber) throws IOException;

List<FireAddress> fireAddress(String address) throws IOException;

List<FireAddress> floodStation(List<String> aListOfStationNumber) throws IOException;

List<PersonInfo> personInfo(String firstName, String lastName) throws IOException;

List<CommunityEmail> communityEmail(String city) throws IOException;

}
