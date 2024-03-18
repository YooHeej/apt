package com.example.aptProject.service;

import com.example.aptProject.dao.MyRegionDao;
import com.example.aptProject.entity.LocationCode;
import com.example.aptProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRegionServiceImpl implements MyRegionService{
    @Autowired private MyRegionDao mDao;
    @Autowired private LocationService lSvc;
    @Override
    public void registerUser(Users user, String firstName, String secondName) {
        LocationCode locationCode = lSvc.getLocationCodeByFirstNameAndSecondName(firstName, secondName);
        mDao.insert(user.getUid(), locationCode.getlCode());
    }
}
