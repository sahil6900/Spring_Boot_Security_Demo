package com.spring.security.test.demo.controller;

import com.spring.security.test.demo.entity.Roles;
import com.spring.security.test.demo.entity.User;
import com.spring.security.test.demo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    //---------------------*note*--------------------
    // here in this postman cannot be used with post mapping with postman tool so need to use
    //get mapping with this
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Roles> saveRole(@RequestBody Roles role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/get/{uId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer uId) {
        return ResponseEntity.ok().body(userService.getUser(uId));
    }

    @GetMapping("/user/get/username/{username}")
    public ResponseEntity<User>getUserByUserName(@PathVariable String username){
        User userByUserName = userService.getUserByUserName(username);
        System.out.println(userByUserName);
        return ResponseEntity.ok().body(userService.getUserByUserName(username));
    }
}

@Data
class RoleToUserForm {
    private String userName;
    private String roleName;
}
