package com.example.bloodBank.controller;

import com.example.bloodBank.entity.Address;
import com.example.bloodBank.entity.Feedback;
import com.example.bloodBank.entity.User;
import com.example.bloodBank.model.UserModel;
import com.example.bloodBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //TODO--1: user registration

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User registration(@RequestBody UserModel user){
        return userService.registration(user);
    }
    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address){
        return userService.createAddress(address);
    }



    //TODO 4:User will be able to request for blood mentioning the location (same as before), relationship with the patient, alternate mobile number, time frame.

    @PostMapping("/{donorId}/{bloodRequestId}")
    public UserModel bloodDonationRequest(@PathVariable Integer donorId,@PathVariable Integer bloodRequestId) throws
            ParseException {
        return userService.bloodDonationRequest(donorId,bloodRequestId);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    @GetMapping("/activeDonor")
    public List<UserModel> getAllActiveDonor(){
        return userService.getAllActiveDonor();
    }


    //TODO 7:Both users will be able to give feedback.

    @PostMapping("/feedback/{bloodRequestId}")
    public void giveFeedback(@RequestBody Feedback feedback,@PathVariable Integer bloodRequestId){
        userService.giveFeedback(feedback,bloodRequestId);
    }
}
