package com.abcz.TempProject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abcz.TempProject.domain.Role;
import com.abcz.TempProject.domain.User;
import com.abcz.TempProject.repo.RoleRepo;
import com.abcz.TempProject.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(userName);
		String message = "";
		if (user == null) {
			message = "User not found in the database";
			log.error(message);
		} else {
			message = "User found in the database : {}";
			log.info(message, userName);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(),
				authorities);
	}

	@Override
	public User saveUser(User user) {
		log.info("Saving new user {} to the database", user.getName());
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		log.info("Adding role {} to user {} ", roleName, userName);
		User user = userRepo.findByUserName(userName);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String userName) {
		log.info("Fetching user {} to user {} ", userName);
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

}
