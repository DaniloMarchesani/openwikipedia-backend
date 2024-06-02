package me.danilomarchesani.openwikipedia.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.danilomarchesani.openwikipedia.model.Role;

import java.util.*;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
