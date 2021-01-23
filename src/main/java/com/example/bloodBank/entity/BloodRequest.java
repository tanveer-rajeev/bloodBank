package com.example.bloodBank.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String bloodGroup;
    private String areaZone;
    private String  donationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "bloodRequest")
    private Set<User> donationRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "bloodRequest")
    private List<Feedback>  feedbacks;
}
