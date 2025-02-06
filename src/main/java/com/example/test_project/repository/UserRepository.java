package com.example.test_project.repository;

import com.example.test_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Boolean findByName(String email);

    Optional<User> findByEmail(String email);
}
