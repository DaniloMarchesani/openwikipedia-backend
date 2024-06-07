package me.danilomarchesani.openwikipedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String username;
    @JsonIgnore
    @Size(min = 6, max = 15)
    private String password;

    private String firstname;
    private String lastname;
    private Address address;

    @NotBlank
    @Email
    private String email;

    private Date createdAt = new Date();
    private boolean isEnabled = true;

    private Set<Role> roles;
}
