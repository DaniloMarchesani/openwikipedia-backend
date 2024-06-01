package me.danilomarchesani.openwikipedia.service;

import me.danilomarchesani.openwikipedia.errors.RoleNotFoundException;
import me.danilomarchesani.openwikipedia.model.ERole;
import me.danilomarchesani.openwikipedia.model.Role;
import me.danilomarchesani.openwikipedia.model.User;
import me.danilomarchesani.openwikipedia.repository.RoleRepository;
import me.danilomarchesani.openwikipedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public Stream<User> findAllUsersByRole(Role role){
        Set<User> users = new HashSet<>();
        users = roleRepository.findUsersByRole(role);

        return users.stream();
    }

    /**
     * This method adds a roel to a specific user.
     * @param role
     * @param user
     * @return role added
     * @author Danilo M. 31/05/2024
     */
    public Role addRoleToUser(Role role, User user) {
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
        return role;
    }

    /**
     * This method permit to delete an authority to a user.
     * @param role
     * @param user
     * @author Danilo M. 31/05/2024
     */
    public void removeARoleToAUser(Role role, User user) throws Exception {
        try {
            Set<Role> userRoles = user.getRoles();
            userRoles.remove(role);
            user.setRoles(userRoles);
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    public Role findByRole(ERole eRole) throws RoleNotFoundException {
        try {
            return roleRepository.findByRole(eRole);
        } catch (RoleNotFoundException e) {
            throw new RoleNotFoundException("role not found: " + e.getMessage());
        }
    }
}
