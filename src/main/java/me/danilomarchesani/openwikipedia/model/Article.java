package me.danilomarchesani.openwikipedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "articles")
public class Article {

    @Id
    private String id;

    private String title;
    private String content;
    private Date savedOnDate;
    private Date lastModifiedDate;

}
