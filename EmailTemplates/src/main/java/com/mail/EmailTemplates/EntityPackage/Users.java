package com.mail.EmailTemplates.EntityPackage;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Integer id;
    private String username;
    private String mail;
    private  String password;
}
