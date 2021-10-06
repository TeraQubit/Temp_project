package com.abcz.TempProject.service;

import java.util.List;

import com.abcz.TempProject.domain.Role;
import com.abcz.TempProject.domain.User;

public interface UserService {
	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String userName, String roleName);

	User getUser(String userName);

	List<User> getUsers();
}
