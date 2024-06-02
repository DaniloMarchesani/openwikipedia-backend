package me.danilomarchesani.openwikipedia;

import me.danilomarchesani.openwikipedia.model.ERole;
import me.danilomarchesani.openwikipedia.model.Role;
import me.danilomarchesani.openwikipedia.model.User;
import me.danilomarchesani.openwikipedia.repository.RoleRepository;
import me.danilomarchesani.openwikipedia.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OpenwikipediaApplication implements CommandLineRunner {

	@Autowired
	private final static Logger logger = LoggerFactory.getLogger(OpenwikipediaApplication.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(OpenwikipediaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(roleRepository.findAll().isEmpty()){
			Role r_user = new Role();
			r_user.setRole(ERole.ROLE_USER);

			Role r_admin = new Role();
			r_admin.setRole(ERole.ROLE_ADMIN);

			Role r_author = new Role();
			r_author.setRole(ERole.ROLE_AUTHOR);

			roleRepository.save(r_user);
			roleRepository.save(r_admin);
			roleRepository.save(r_author);
		}


		if(userRepository.findAll().isEmpty()){

			User user = new User();
			user.setEmail("danilom@hotmail.com");
			user.setFirstname("Danilo");
			user.setLastname("Marchesani");
			user.setCreatedAt(new Date());
			user.setPassword("1234danilo");
			user.setUsername("danimaster");
			Set<Role> role = new HashSet<>();
			role.add(roleRepository.findByRole(ERole.ROLE_USER));
			user.setRoles(role);

			userRepository.save(user);
		}
	}
}
