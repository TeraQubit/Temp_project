package com.abcz.TempProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcz.TempProject.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByUserName(String userName);
	
}
