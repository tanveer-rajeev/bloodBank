package com.example.bloodBank.repository;

import com.example.bloodBank.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRequestRepository extends JpaRepository<BloodRequest,Integer> {
    BloodRequest findByBloodGroup(String bloodGroup);
}
