package me.danilomarchesani.openwikipedia.repository;

import me.danilomarchesani.openwikipedia.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import me.danilomarchesani.openwikipedia.model.ArticleHistory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleHistoryRepository extends MongoRepository<ArticleHistory, String> {
    Set<ArticleHistory> findByArticle(Article article);
}
