package me.danilomarchesani.openwikipedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "articles_history")
public class ArticleHistory {
    @Id
    private String id;

    private String title;
    private String content;

    private String articleId;
    private Date modifiedAt = new Date();
}
