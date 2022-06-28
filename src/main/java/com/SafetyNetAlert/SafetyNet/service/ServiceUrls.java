package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.*;

import java.io.IOException;
import java.util.List;

public interface ServiceUrls{

List<ChildAlert> childAlert(String address) throws IOException;

PhoneNumber phoneNumber(String stationNumber) throws IOException;

List<FireAddress> fireAddress(String address) throws IOException;

FloodStation floodStation(String aListOfStationNumber) throws IOException;

PersonInfo personInfo(String firstName, String lastName) throws IOException;

List<CommunityEmail> communityEmail(String city) throws IOException;
}
