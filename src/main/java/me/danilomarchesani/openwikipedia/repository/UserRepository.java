package me.danilomarchesani.openwikipedia.repository;

import me.danilomarchesani.openwikipedia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Set<User> findAllByUsername(String firstname);

    Set<User> findAllByLastname(String lastname);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
