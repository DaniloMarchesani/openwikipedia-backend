package me.danilomarchesani.openwikipedia.controllers;

import jakarta.validation.Valid;
import me.danilomarchesani.openwikipedia.model.User;
import me.danilomarchesani.openwikipedia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByIdController(@Valid @RequestParam String id) throws Exception {
            User userToFind = userService.findUserById(id);
            return ResponseEntity.ok(userToFind);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@Valid @RequestParam String username) throws Exception {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User userUpdated) throws Exception {
        userService.updateUser(userUpdated);
        return ResponseEntity.ok("User updated Successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @RequestParam String id) throws Exception {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully: " + id);
    }
}
