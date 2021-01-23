package com.example.bloodBank.model;

import com.example.bloodBank.entity.Address;
import com.example.bloodBank.entity.BloodRequest;
import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private String name;
    private String mobileNumber;
    private String alternateMobileNumber;
    private String email;
    private String bloodGroup;
    private String religion;
    private String weight;
    private String dateOfBirth;
    private String lastDonationDate;
    private boolean activeDonor;
    private Address address;
    private BloodRequest bloodRequest;
}
