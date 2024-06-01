package me.danilomarchesani.openwikipedia.service;

import me.danilomarchesani.openwikipedia.errors.DailyArticleNotFoundException;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.model.DailyArticle;
import me.danilomarchesani.openwikipedia.repository.DailyArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DailyArticleService {

    @Autowired
    private DailyArticleRepository dailyArticleRepository;

    public DailyArticle saveDailyArticle(Article article) throws Exception {
        try {
            DailyArticle dArticle = new DailyArticle();
            dArticle.setArticle(article);
            dArticle.setTodayDate(new Date());
            return dArticle;
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    public DailyArticle getTodayArticle(Date date) throws Exception {
        try {
            Optional<DailyArticle> dArticle = dailyArticleRepository.findByTodayDate(date);
            if(!dArticle.isPresent()) throw new DailyArticleNotFoundException("Daily article not found!");
            return dArticle.get();
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    public void deleteDailyArticle(String id) throws Exception {
        try {
            dailyArticleRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }
}
