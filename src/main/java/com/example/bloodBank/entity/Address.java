package com.example.bloodBank.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String unions;
    private String postOffice;
    private String policeStation;
    private String district;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "address")
    private Set<User> users;

}
