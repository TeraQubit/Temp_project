package com.abcz.TempProject;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abcz.TempProject.domain.Role;
import com.abcz.TempProject.domain.User;
import com.abcz.TempProject.service.UserService;

@SpringBootApplication
public class TempProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Nguyen Van Truong", "truongnv", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Nguyen Van A", "anv", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Nguyen Van B", "bnv", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Nguyen Van C", "cnv", "1234", new ArrayList<>()));

			userService.addRoleToUser("truongnv", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("truongnv", "ROLE_ADMIN");
			userService.addRoleToUser("anv", "ROLE_MANAGER");
			userService.addRoleToUser("bnv", "ROLE_ADMIN");
			userService.addRoleToUser("cnv", "ROLE_USER");
			userService.addRoleToUser("cnv", "ROLE_MANAGER");
		};
	}
}
