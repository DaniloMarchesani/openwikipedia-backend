package me.danilomarchesani.openwikipedia.controllers;

import jakarta.validation.Valid;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.service.ArticleService;
import me.danilomarchesani.openwikipedia.service.DailyArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DailyArticleController {

    @Autowired
    private DailyArticleService  dailyArticleService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCurrentDailyArticle(@Valid @RequestBody Article article) throws Exception {
        dailyArticleService.saveDailyArticle(article);
        articleService.save(article);
        return ResponseEntity.ok("Daily article saved successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDailyArticle(@Valid @RequestParam String id) throws Exception {
        dailyArticleService.deleteDailyArticle(id);
        return ResponseEntity.ok("Daily article deleted successfully: " + id);
    }
}
