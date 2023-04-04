package com.spring.security.test.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(name = "uc_user_username", columnNames = {"username"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uId;

    private String uName;
    private String uSurname;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Collection<Roles> roles = new ArrayList<>();
}
