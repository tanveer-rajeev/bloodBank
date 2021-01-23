package com.example.bloodBank.service;

import com.example.bloodBank.entity.Admin;
import com.example.bloodBank.entity.BloodRequest;
import com.example.bloodBank.repository.AdminRepository;
import com.example.bloodBank.repository.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BloodRequestRepository bloodRequestRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository , BloodRequestRepository bloodRequestRepository) {
        this.adminRepository = adminRepository;
        this.bloodRequestRepository = bloodRequestRepository;
    }


    public Admin createAdmin(Admin admin){
        return adminRepository.save(admin);
    }


    public BloodRequest requestForBlood(BloodRequest bloodRequest){

        return bloodRequestRepository.save(bloodRequest);
    }


    public void deleteBloodRequest(Integer bloodRequestId) {
        bloodRequestRepository.deleteById(bloodRequestId);
    }
}
