package com.example.aptProject.service;

import com.example.aptProject.entity.LocationCode;

import java.util.List;

public interface LocationService {
    String getLocationName(int lCode);
    List<String> getAllFirstNames();
    List<String> getSecondNamesByFirstName(String firstName);
    LocationCode getLocationCodeByFirstNameAndSecondName(String firstName, String secondName);
}
