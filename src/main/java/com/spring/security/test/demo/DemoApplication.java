package com.spring.security.test.demo;

import com.spring.security.test.demo.entity.Roles;
import com.spring.security.test.demo.entity.User;
import com.spring.security.test.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    protected BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//	@Bean
//	CommandLineRunner commandLineRunner(UserService userService){
//		return args -> {
//
//			userService.saveRole(new Roles(5,"Role_Manager"));
//			userService.saveRole(new Roles(6,"Role_User"));
//			userService.saveRole(new Roles(7,"Role_Admin"));
//			userService.saveRole(new Roles(8,"Role_SuperUser"));
//
//			userService.saveUser(new User(1,"sahil","pawar","sspawar","sahil123",new ArrayList<>()));
//			userService.saveUser(new User(2,"darshan","mahajan","dmahajan","1234",new ArrayList<>()));
//			userService.saveUser(new User(3,"aniket","more","ani","3456",new ArrayList<>()));
//			userService.saveUser(new User(4,"ketki","pawar","ketu","7777",new ArrayList<>()));
//
//			userService.addRoleToUser("sahil","Role_Manager");
//			userService.addRoleToUser("darshan","Role_User");
//			userService.addRoleToUser("aniket","Role_Admin");
//			userService.addRoleToUser("ketki","Role_SuperUser");
//			userService.addRoleToUser("sahil","Role_Admin");
//			userService.addRoleToUser("sahil","Role_SuperUser");
//
//
//		};
//	}

}
