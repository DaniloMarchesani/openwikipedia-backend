package me.danilomarchesani.openwikipedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import me.danilomarchesani.openwikipedia.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    Optional<Article> findById(String id);


    Optional<List<Article>> findByUserId(String id);

    Page<Article> findAll(Pageable pageable);

    Optional<Article> findByTitle(String articleTitle);

    Set<Article> findManyArticlesByTitle(String articleTitle);
}
