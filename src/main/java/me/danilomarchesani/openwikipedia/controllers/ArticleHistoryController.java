package me.danilomarchesani.openwikipedia.controllers;

import jakarta.validation.Valid;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.model.ArticleHistory;
import me.danilomarchesani.openwikipedia.repository.ArticleHistoryRepository;
import me.danilomarchesani.openwikipedia.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleHistoryController {
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleHistory(@Valid @PathVariable String id) throws Exception {
        Set<ArticleHistory> articleHistories = articleHistoryRepository.findByArticleId(articleService.getArticleById(id).getId());
        return ResponseEntity.ok(articleHistories);
    }

    @PutMapping("/restore")
    public ResponseEntity<?> restoreArticleContentFromHistory(@Valid @RequestBody ArticleHistory articleToRestore) throws Exception {
        Article article = articleService.getArticleById(articleToRestore.getArticleId());
        article.setContent(articleToRestore.getContent());
        article.setLastModifiedDate(new Date());
        articleService.updateArticle(article.getId(), article);
        return ResponseEntity.ok(article);
    }

}
