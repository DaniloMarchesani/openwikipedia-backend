package me.danilomarchesani.openwikipedia.repository;

import me.danilomarchesani.openwikipedia.model.DailyArticle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DailyArticleRepository extends MongoRepository<DailyArticle, String> {
    Optional<DailyArticle> findByTodayDate(Date date);
}
