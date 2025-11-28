package com.trendbazaar.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name not blank")
    private String fullName;
    @Column(nullable = false)
    @NotBlank(message = "password not blank")
    private String password;
    @Column(unique = true)
    private String email;

    private String role ;
}
