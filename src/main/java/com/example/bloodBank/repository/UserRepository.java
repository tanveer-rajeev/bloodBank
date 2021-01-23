package com.example.bloodBank.repository;

import com.example.bloodBank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     User findByEmail(String email);
     User findByMobileNumber(String mobileNumber);

}
