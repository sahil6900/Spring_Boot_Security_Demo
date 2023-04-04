package com.spring.security.test.demo.service;

import com.spring.security.test.demo.entity.Roles;
import com.spring.security.test.demo.entity.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);

    public User getUser(Integer uId);

    public User getUserByUserName(String username);

    public List<User> getAllUsers();

    public Roles saveRole(Roles role);

    void addRoleToUser(String username, String roleName);


}
