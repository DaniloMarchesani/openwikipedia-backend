package me.danilomarchesani.openwikipedia.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roles")
@Data
public class Role {
    @Id
    private String id;

    private ERole role;
}
