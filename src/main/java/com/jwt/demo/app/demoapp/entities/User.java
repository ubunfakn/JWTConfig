package com.jwt.demo.app.demoapp.entities;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;


//This is an entity and userdetails class

@Data //from lombok it will automatically generate getters and setters
        //toString etc methods by default

@Builder

@NoArgsConstructor //from lombok to generate unparameterized constructors

@AllArgsConstructor //from lombok to generate parameterized constructors

@Entity //From jakarta.persistence to let JPA know that it has to make
        //schema of this class

@Table(name = "user") // Declaring table name(Optional)

public class User implements UserDetails {
    

    @Id //To let JPA know this will be the primary key for the table
    @GeneratedValue
    public Integer id;
    public String firstName;
    public String lastName;
    public String pass;
    public String email;

    @Enumerated(EnumType.STRING)//To specify Enum object
    private Role role;//Role is an Enum here


    //Implementing all abstract methods of UserDetails interface

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword() {
        return pass;
    }    
}
