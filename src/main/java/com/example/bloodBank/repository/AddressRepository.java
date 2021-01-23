package com.example.bloodBank.repository;

import com.example.bloodBank.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
      Address findByPostOffice(String postOffice);
}
