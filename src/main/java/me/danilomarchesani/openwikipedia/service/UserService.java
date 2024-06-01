package me.danilomarchesani.openwikipedia.service;

import me.danilomarchesani.openwikipedia.errors.UserNotCreatedException;
import me.danilomarchesani.openwikipedia.errors.UserNotFoundException;
import me.danilomarchesani.openwikipedia.model.User;
import me.danilomarchesani.openwikipedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (UserNotCreatedException e) {
            throw new UserNotCreatedException("An Error occurred while creating the new user: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }

    public User findByUsername(String username) throws Exception {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            if(!user.isPresent()) throw new UserNotFoundException("User with username: " + username + " doesn't exist.");
            return user.get();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public Stream<User> findUsersByFirstname(String firstname) throws Exception {
        try {
            Set<User> users = new HashSet<>();
            users = userRepository.findAllByUsername(firstname);
            return users.stream();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public Stream<User> findUsersByLastname(String lastname) throws Exception {
        try {
            Set<User> users = new HashSet<>();
            users = userRepository.findAllByLastname(lastname);
            return users.stream();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public User findUserById(String id) throws Exception {
        try {
            if(!userRepository.existsById(id)) throw new UserNotFoundException("User with id: " + id + " seems that doesn't exist!");
            Optional<User> user = userRepository.findById(id);
            if(!user.isPresent()) throw new UserNotFoundException("User with id: " + id + " seems that doesn't exist!");
            return user.get();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public User updateUser(User userUpdated) throws Exception {
        try {
            User user = userRepository.findById(userUpdated.getId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userUpdated.getId()));
            user.setEmail(userUpdated.getEmail());
            user.setUsername(userUpdated.getUsername());
            user.setFirstname(userUpdated.getFirstname());
            user.setLastname(userUpdated.getLastname());
            user.setRoles(userUpdated.getRoles());
            user.setAddress(userUpdated.getAddress());
            user.setPassword(userUpdated.getPassword());
            userRepository.save(user);
            return user;
        } catch(Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean checkifEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
