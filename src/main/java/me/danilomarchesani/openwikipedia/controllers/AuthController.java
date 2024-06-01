package me.danilomarchesani.openwikipedia.controllers;

import jakarta.validation.Valid;
import me.danilomarchesani.openwikipedia.model.ERole;
import me.danilomarchesani.openwikipedia.model.Role;
import me.danilomarchesani.openwikipedia.model.User;
import me.danilomarchesani.openwikipedia.payload.request.LoginRequest;
import me.danilomarchesani.openwikipedia.payload.request.RegisterRequest;
import me.danilomarchesani.openwikipedia.payload.response.JwtResponse;
import me.danilomarchesani.openwikipedia.repository.UserRepository;
import me.danilomarchesani.openwikipedia.security.jwt.JwtUtils;
import me.danilomarchesani.openwikipedia.security.service.UserDetailsImpl;
import me.danilomarchesani.openwikipedia.service.RoleService;
import me.danilomarchesani.openwikipedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v0.1.0/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            Set<String> roles = userDetails.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toSet());

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), userDetails.getId(), roles));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws RuntimeException {

        try {
            if (userService.checkIfUsernameExists(registerRequest.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already Taken!");
            }

            if (userService.checkifEmailExists(registerRequest.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered!");
            }

            //Register a new user!
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setRoles(registerRequest.getRoles());
            user.setFirstname(registerRequest.getFirstname());
            user.setLastname(registerRequest.getLastname());
            user.setAddress(registerRequest.getAddress());

            Set<Role> roles = new HashSet<>();

            if (user.getRoles() == null) {
                Role role = roleService.findByRole(ERole.ROLE_USER);
                user.getRoles().add(role);
            } else {
                user.getRoles().forEach(role -> {
                    switch (role.toString()) {
                        case "admin":
                            Role adminRole = roleService.findByRole(ERole.ROLE_ADMIN);
                            roles.add(adminRole);
                            break;

                        case "author":
                            Role authorRole = roleService.findByRole(ERole.ROLE_AUTHOR);
                            roles.add(authorRole);
                            break;

                        default:
                            Role userRole = roleService.findByRole(ERole.ROLE_USER);
                            roles.add(userRole);

                    }
                });
            }

            user.setRoles(roles);
            userService.createUser(user);

            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
