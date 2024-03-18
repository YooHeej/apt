package com.example.aptProject.service;

import com.example.aptProject.dao.LocationCodeDao;
import com.example.aptProject.entity.LocationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationCodeDao locationCodeDao;

    @Override
    public List<String> getAllFirstNames() {
        return locationCodeDao.findAllFirstNames();
    }

    @Override
    public List<String> getSecondNamesByFirstName(String firstName) {
        List<String> list = locationCodeDao.findSecondNamesByFirstName(firstName);
        list.removeIf(item -> item == null);
        Collections.sort(list);


        return list;
    }

    @Override
    public LocationCode getLocationCodeByFirstNameAndSecondName(String firstName, String secondName) {
        return locationCodeDao.getLocationCodeByFirstNameAndSecondName(firstName, secondName);
    }
    @Override
    public String getLocationName(int lCode) {
        String firstName, secondName, lastName = "";

        firstName = locationCodeDao.getLocationCode(lCode).getFirstName();

        secondName = locationCodeDao.getLocationCode(lCode).getSecondName();
        if(secondName == null){
            return firstName;
        }
        lastName = locationCodeDao.getLocationCode(lCode).getLastName();
        if(lastName == null){
            return firstName + " " + secondName;
        }

        return firstName + " " + secondName + " " + lastName;
    }
}
