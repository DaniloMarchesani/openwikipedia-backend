package me.danilomarchesani.openwikipedia.service;

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    public Stream<User> findUsersByFirstname(String firstname) {
        Set<User> users = new HashSet<>();
        users = userRepository.findAllByUsername(firstname);
        return users.stream();
    }

    public Stream<User> findUsersByLastname(String lastname) {
        Set<User> users = new HashSet<>();
        users = userRepository.findAllByLastname(lastname);
        return users.stream();
    }

    public Optional<User> findUserById(String id) {
        if(!userRepository.existsById(id)) throw new UserNotFoundException("User with id: " + id + " seems that doesn't exist!");
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean checkifEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }




}
