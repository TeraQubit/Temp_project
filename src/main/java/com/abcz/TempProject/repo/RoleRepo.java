package com.abcz.TempProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcz.TempProject.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
