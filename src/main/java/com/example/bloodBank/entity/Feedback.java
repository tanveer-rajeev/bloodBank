package com.example.bloodBank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String comment;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bloodRequestId")
    private BloodRequest bloodRequest;
}
