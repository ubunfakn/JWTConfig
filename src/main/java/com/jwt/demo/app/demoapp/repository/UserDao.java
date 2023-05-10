package com.jwt.demo.app.demoapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.demo.app.demoapp.entities.User;

//Interface extending JpaRepository to access the database 
public interface UserDao extends JpaRepository<User, Integer>{
    
    //Abstract method to find and return user by email from
    // the Database 

    
    public Optional<User> findByEmail(String email);


    //This method does not require any implementation this 
    //is a pre-defined method in spring boot we just have
    //to call it

}
