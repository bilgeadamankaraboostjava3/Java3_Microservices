package com.muhammet.repository.entity;

import com.muhammet.repository.enums.Activated;
import com.muhammet.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblauth")
@Entity
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 16,nullable = false,unique = true)
    String username;
    String password;
    String email;
    String phone;
    @Enumerated(EnumType.STRING)
    Activated activated;
    @Enumerated(EnumType.STRING)
    Roles roles;
}
