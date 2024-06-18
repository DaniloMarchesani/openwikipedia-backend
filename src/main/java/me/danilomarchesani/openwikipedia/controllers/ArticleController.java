package me.danilomarchesani.openwikipedia.controllers;

import jakarta.validation.Valid;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.model.ArticleHistory;
import me.danilomarchesani.openwikipedia.service.ArticleHistoryService;
import me.danilomarchesani.openwikipedia.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleHistoryService articleHistoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllUserArticles() throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUser = auth.getName();
            return ResponseEntity.ok(articleService.getAllArticlesOfUsername(currentUser));
        } catch(Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleByTitle(@Valid @PathVariable String id) throws Exception {
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Set<ArticleHistory>> getHitoryofAnArticle(@PathVariable String id) throws Exception {
        Article article = articleService.getArticleById(id);
        Set<ArticleHistory> history = articleHistoryService.getArticleHistory(article);
        return ResponseEntity.ok(history);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveArticleLocally(@Valid @RequestBody Article article) throws Exception {
        articleService.save(article);
        return ResponseEntity.ok("Article " + article.getTitle() + " saved locally");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Article> updateArticle(@Valid @RequestBody Article updatedArticle, @PathVariable String id) throws Exception {
        ArticleHistory snapshot = articleHistoryService.createArticleHistory(articleService.getArticleById(id));
        Article article = articleService.updateArticle(id, updatedArticle);
        return ResponseEntity.ok(article);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable String id) throws Exception {
        articleService.deleteArticle(id);
        articleHistoryService.deleteAllArticleHisotries(id);
        return ResponseEntity.ok("Article deleted: " + id);
    }
}
