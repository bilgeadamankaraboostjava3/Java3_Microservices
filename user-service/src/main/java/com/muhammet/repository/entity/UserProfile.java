package com.muhammet.repository.entity;

import javax.persistence.*;

@Table(name = "tbluserprofile")
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Auth servisinden kayıt olan kişinin auth id sini buraya eşitliyoruz.
     */
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;

}
