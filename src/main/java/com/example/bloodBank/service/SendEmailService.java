package com.example.bloodBank.service;

import com.example.bloodBank.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendEmailService {


    private final JavaMailSender javaMailSender;
    private final AddressRepository addressRepository;

    @Autowired
    public SendEmailService(JavaMailSender javaMailSender , AddressRepository addressRepository) {
        this.javaMailSender = javaMailSender;
        this.addressRepository = addressRepository;
    }

    public void sendEmail(List<String> to , String body, String topic){
        System.out.println("sending email");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("tanviraj1234@gmail.com");

        for ( String toEmail:to) {
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(topic);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
        }



        System.out.println("sent email....");
    }
}
