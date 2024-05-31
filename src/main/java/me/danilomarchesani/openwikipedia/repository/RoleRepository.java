package me.danilomarchesani.openwikipedia.repository;

import me.danilomarchesani.openwikipedia.model.ERole;
import me.danilomarchesani.openwikipedia.model.Role;
import me.danilomarchesani.openwikipedia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Set<User> findUsersByRole(Role role);

    Role findByRole(ERole eRole);
}
