package edu.cmu.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repo for fetching profile data.
 *
 */
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Profile findByUsername(final String username);
}
