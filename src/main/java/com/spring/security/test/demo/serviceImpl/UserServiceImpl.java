package com.spring.security.test.demo.serviceImpl;

import com.spring.security.test.demo.entity.Roles;
import com.spring.security.test.demo.entity.User;
import com.spring.security.test.demo.repo.RoleRepo;
import com.spring.security.test.demo.repo.UserRepo;
import com.spring.security.test.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepo userRepo;


    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1 = userRepo.findByUsername(username);
        if (user1 == null) {
            log.error("User not found with username :{}", username);
            throw new UsernameNotFoundException("User not found with username :" + username);
        } else {
            log.info("User not found with username :{}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user1.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info("added roles to autorized{}:",authorities);
        return new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("saving user {} with password {} ", user.getUsername(), user.getPassword());
        return userRepo.save(user);
    }

    @Override
    public User getUser(Integer uId) {
        log.info("Getting user with user uId{}", uId);
        return userRepo.findById(uId).get();
    }

    @Override
    public User getUserByUserName(String username) {
        log.info("Getting user by username{}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepo.findAll();
    }

    @Override
    public Roles saveRole(Roles role) {
        log.info("Saving role{}", role);
        return roleRepo.save(role);
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Saving user with username{} and roleName{}", username, roleName);
        User user = userRepo.findByUsername(username);
        Roles roles = roleRepo.findRolesByName(roleName);
        user.getRoles().add(roles);
    }


}
