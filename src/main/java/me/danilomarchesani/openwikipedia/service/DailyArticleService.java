package me.danilomarchesani.openwikipedia.service;

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

    public DailyArticle saveDailyArticle(Article article) {
        DailyArticle dArticle = new DailyArticle();
        dArticle.setArticle(article);
        dArticle.setTodayDate(new Date());
        return dArticle;
    }

    public Optional<DailyArticle> getTodayArticle(Date date) {
        Optional<DailyArticle> dArticle = dailyArticleRepository.findByTodayDate(date);
        return dArticle;
    }
}
