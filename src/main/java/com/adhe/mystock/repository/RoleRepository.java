package com.adhe.mystock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adhe.mystock.model.Role;
import com.adhe.mystock.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(RoleName roleName);
}
	