package me.danilomarchesani.openwikipedia.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.danilomarchesani.openwikipedia.model.Address;
import me.danilomarchesani.openwikipedia.model.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 15)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 15)
    private String lastname;

    private Set<Role> roles;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    private Address address;

}
