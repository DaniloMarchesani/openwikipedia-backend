package me.danilomarchesani.openwikipedia.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;


@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String username;
    @Size(min = 6, max = 15)
    private String password;

    private String firstname;
    private String lastname;

    private Address address;

    @NotBlank
    @Email
    private String email;

    private LocalDate createdAt;
    private boolean isEnabled;

    private Set<Role> roles;
}
