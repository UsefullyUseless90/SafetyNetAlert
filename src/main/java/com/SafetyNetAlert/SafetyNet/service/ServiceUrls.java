package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.Person;

import java.util.List;

public interface ServiceUrls {

List<?> childAlert();
List<?> phoneAlert();
List<?> fireAddress();
List<?> floodStation();
List<?> personInfo();
List<Person> communityMail();
}
