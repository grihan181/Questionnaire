package com.opencode.practice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.opencode.practice.model.Role;
import com.opencode.practice.model.Status;
import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

	@Bean
	CommandLineRunner run(UserRepo userService) {
		return args -> {

			boolean test = userService.existsByEmail("admin@gmail.com");
			if (!userService.existsByEmail("admin@gmail.com")) {
				User user = new User();
				user.setEmail("admin@gmail.com");
				user.setUsername("--AdmiN--");
				user.setPassword("666666");

				user.setPassword(String.valueOf(new BCryptPasswordEncoder(12).encode(user.getPassword())));
				user.setRole(Role.ADMIN);
				user.setStatus(Status.ACTIVE);
				userService.save(user);
			}
		};
	}
}
