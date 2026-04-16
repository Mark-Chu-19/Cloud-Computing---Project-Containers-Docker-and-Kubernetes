package edu.cmu;

import edu.cmu.model.Profile;
import edu.cmu.model.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfileApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

    /**
     * Pre-load the database with sample profiles for demonstration.
     */
    @Bean
    public CommandLineRunner loadData(ProfileRepository repository) {
        return (args) -> {
            // save a few professional sample profiles
            repository.save(new Profile("alice", "Alice Smith", "Female", 25, "https://api.dicebear.com/7.x/avataaars/svg?seed=Alice"));
            repository.save(new Profile("bob", "Bob Johnson", "Male", 30, "https://api.dicebear.com/7.x/avataaars/svg?seed=Bob"));
            repository.save(new Profile("charlie", "Charlie Brown", "Non-binary", 22, "https://api.dicebear.com/7.x/avataaars/svg?seed=Charlie"));

            // fetch all profiles
            LOGGER.info("Profiles found with findAll():");
            LOGGER.info("-------------------------------");
            for (Profile profile : repository.findAll()) {
                LOGGER.info(profile.toString());
            }
            LOGGER.info("");

            // fetch an individual profile by ID
            repository.findById(1L).ifPresent(profile -> {
                LOGGER.info("Profile found with findById(1L):");
                LOGGER.info("--------------------------------");
                LOGGER.info(profile.toString());
                LOGGER.info("");
            });
        };
    }
}
