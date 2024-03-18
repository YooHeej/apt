package com.example.aptProject.service;


import com.example.aptProject.entity.Users;

public interface MyRegionService {
    void registerUser(Users user,String firstName,String secondName);
    void updateUser(Users user, String firstName, String secondName);
}
