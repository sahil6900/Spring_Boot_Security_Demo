package com.spring.security.test.demo.repo;

import com.spring.security.test.demo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles, Integer> {

    Roles findRolesByName(String name);
}
