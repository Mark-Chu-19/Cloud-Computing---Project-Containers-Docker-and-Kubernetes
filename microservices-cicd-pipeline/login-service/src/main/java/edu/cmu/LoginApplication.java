package edu.cmu;

import edu.cmu.model.UserCredential;
import edu.cmu.model.UserCredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserCredentialRepository repository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return (args) -> {
            // Initialize professional demo accounts
            repository.save(new UserCredential("alice", passwordEncoder.encode("password")));
            repository.save(new UserCredential("bob", passwordEncoder.encode("password")));
            repository.save(new UserCredential("charlie", passwordEncoder.encode("password")));

            LOGGER.info("UserCredentials initialized for demo.");
        };
    }
}
