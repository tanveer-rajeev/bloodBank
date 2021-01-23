package com.example.bloodBank.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@ToString
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bloodRequestId")
    private BloodRequest bloodRequest;
}
