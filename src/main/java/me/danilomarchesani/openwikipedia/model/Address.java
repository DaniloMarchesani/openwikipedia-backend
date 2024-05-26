package me.danilomarchesani.openwikipedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    private String id;

    private String city;
    private String address;
    private String houseNumber;
    private String postalCode;
}
