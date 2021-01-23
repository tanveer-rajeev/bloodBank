package com.example.bloodBank.service;

import com.example.bloodBank.entity.Address;
import com.example.bloodBank.entity.BloodRequest;
import com.example.bloodBank.entity.Feedback;
import com.example.bloodBank.entity.User;
import com.example.bloodBank.model.UserModel;
import com.example.bloodBank.repository.AddressRepository;
import com.example.bloodBank.repository.BloodRequestRepository;
import com.example.bloodBank.repository.FeedbackRepository;
import com.example.bloodBank.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BloodRequestRepository bloodRequestRepository;
    private final AddressRepository addressRepository;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public UserService(UserRepository userRepository , ModelMapper modelMapper ,
                       BloodRequestRepository bloodRequestRepository , AddressRepository addressRepository ,
                       FeedbackRepository feedbackRepository) {
        this.userRepository         = userRepository;
        this.modelMapper            = modelMapper;
        this.bloodRequestRepository = bloodRequestRepository;

        this.addressRepository  = addressRepository;
        this.feedbackRepository = feedbackRepository;
    }


    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public User registration(UserModel userModel) {
        if (userRepository.findByEmail(userModel.getEmail()) != null) {
            throw new NullPointerException("email already exist");
        }
        User user = modelMapper.map(userModel , User.class);
        Address addressFromUser = addressRepository.findByPostOffice(userModel
                .getAddress()
                .getPostOffice());

        user.setAddress(addressFromUser);

        userRepository.save(user);
        return user;
    }

    public User getUserById(Integer id) {
        return userRepository
                .findById(id)
                .stream()
                .filter(user -> user
                        .getId()
                        .equals(id))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public BloodRequest getBloodRequestById(Integer bloodRequestId) {
        return bloodRequestRepository
                .findById(bloodRequestId)
                .stream()
                .filter(bloodRequest1 -> bloodRequest1
                        .getId()
                        .equals(bloodRequestId))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public UserModel bloodDonationRequest(Integer donorId , Integer bloodRequestId) throws ParseException {

        User user = getUserById(donorId);
        String lastDonationDate = user.getLastDonationDate();
        if (userRepository.findByMobileNumber(user.getMobileNumber()) == null) {
            throw new NullPointerException("User not found in our system.Please create an account first.");
        }


        //TODO 3: The system will not allow a user to donate blood again within the next 3 months.

        if (!checkDonorTimeFrame(lastDonationDate)) {
            throw new NullPointerException(
                    "Because of last donation time less than 90 days the donor not capable for donation");
        }

        BloodRequest bloodRequest = getBloodRequestById(bloodRequestId);

        Set<User> userOfTheBloodRequest = bloodRequest.getDonationRequests();

        // TODO 5.2: And the first three persons will be able to accept the request.
        if (userOfTheBloodRequest.size() >= 3) {
            throw new NullPointerException("Already 3 requests filled. Try another blood request.");
        }

        if (!user
                .getBloodGroup()
                .equals(bloodRequest.getBloodGroup())) {
            throw new NullPointerException("Requested blood group does not match with expected blood group");
        }


        user.setBloodRequest(bloodRequest);
        user.setLastDonationDate(String.valueOf(LocalDate.now()));
        user.setActiveDonor(true);
        userRepository.save(user);
        return modelMapper.map(user , UserModel.class);
    }

    public static boolean checkDonorTimeFrame(String lastDonationTime) throws ParseException {
        if (lastDonationTime == null) return true;

        String currentDate = String.valueOf(LocalDate.now());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = sdf.parse(lastDonationTime);
        Date secondDate = sdf.parse(currentDate);

        long diffInMilliSec = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMilliSec , TimeUnit.MILLISECONDS);

        return diff >= 90;
    }

    public void giveFeedback(Feedback feedback , Integer bloodRequestId) {
        BloodRequest bloodRequest = getBloodRequestById(bloodRequestId);
        feedback.setBloodRequest(bloodRequest);
        feedbackRepository.save(feedback);
    }

    // TODO 7: Both users will be able to give feedback.

    public List<UserModel> getAllActiveDonor() {
        List<User> users = userRepository.findAll();

        List<User> activeUser = users
                .stream()
                .filter(User::isActiveDonor)
                .collect(Collectors.toList());

        return activeUser
                .stream()
                .map(user -> modelMapper.map(user , UserModel.class))
                .collect(Collectors.toList());
    }
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteAddress(Integer id) {

        addressRepository.deleteById(id);
    }

}
