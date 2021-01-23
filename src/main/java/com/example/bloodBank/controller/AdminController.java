package com.example.bloodBank.controller;

import com.example.bloodBank.entity.Admin;
import com.example.bloodBank.entity.BloodRequest;
import com.example.bloodBank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping
    public Admin createAccount(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }


    //TODO 2:Admin user will input the blood donation date in the system.

    @PostMapping("/bloodNeed")
    public BloodRequest requestForBlood(@RequestBody BloodRequest bloodRequest){
          return adminService.requestForBlood(bloodRequest);
    }


    @DeleteMapping("/{bloodRequestId}")
    public void deleteBloodRequest(@PathVariable Integer bloodRequestId){
        adminService.deleteBloodRequest(bloodRequestId);
    }


}
