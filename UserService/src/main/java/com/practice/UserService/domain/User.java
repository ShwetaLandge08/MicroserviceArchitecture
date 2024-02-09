package com.practice.UserService.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name="users")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNo;
    private String name;
}
