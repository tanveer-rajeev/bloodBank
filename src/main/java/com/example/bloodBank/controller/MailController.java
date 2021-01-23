package com.example.bloodBank.controller;

import com.example.bloodBank.entity.BloodRequest;
import com.example.bloodBank.entity.User;
import com.example.bloodBank.repository.BloodRequestRepository;
import com.example.bloodBank.repository.UserRepository;
import com.example.bloodBank.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MailController {


    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;

    @Autowired
    public MailController(SendEmailService sendEmailService , UserRepository userRepository ,
                          BloodRequestRepository bloodRequestRepository) {
        this.sendEmailService       = sendEmailService;
        this.userRepository         = userRepository;
        this.bloodRequestRepository = bloodRequestRepository;
    }



    @PostMapping("/api/sendMail/{bloodRequestId}")
    public void triggerWhenStarts(@PathVariable Integer bloodRequestId) {
        List<User> users = userRepository.findAll();
        BloodRequest bloodRequest = bloodRequestRepository
                .findById(bloodRequestId)
                .stream()
                .filter(bloodRequest1 -> bloodRequest1
                        .getId()
                        .equals(bloodRequestId))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        //TODO 5.1: All user who are in the same area will be notified by mail
        
        List<String> strings = users
                .stream()
                .filter(user -> user
                        .getAddress()
                        .getPoliceStation()
                        .equals(bloodRequest.getAreaZone()))
                .map(User::getEmail)
                .collect(Collectors.toList());

        sendEmailService.sendEmail(strings , "hi,donor" , "Blood request");

    }
}
