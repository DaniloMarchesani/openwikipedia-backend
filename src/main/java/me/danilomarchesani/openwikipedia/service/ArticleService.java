package me.danilomarchesani.openwikipedia.service;

import me.danilomarchesani.openwikipedia.errors.ArticleNotFoundException;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Optional<Article> getArticleByTitle(String articleTitle) {
        Optional<Article> article = articleRepository.findByTitle(articleTitle);
        return article;
    }

    public Stream<Article> getAllArticlesByTitle(String articleTitle) {
        Set<Article> articles = articleRepository.findManyArticlesByTitle(articleTitle);
        return articles.stream();
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> saveMultiple(List<Article> articles) {
        return articleRepository.saveAll(articles);
    }

    public Article updateArticle(String id, Article articleUpdated) {
        if(!articleRepository.existsById(id)) throw new ArticleNotFoundException("Article to update with title: " + articleUpdated.getTitle() + " not found.");

        Article article = new Article();
        article.setId(articleUpdated.getId());
        article.setTitle(articleUpdated.getTitle());
        article.setSavedOnDate(new Date());
        article.setContent(articleUpdated.getContent());
        article.setLastModifiedDate(new Date());

        return articleRepository.save(article);
    }

    public void deleteArticle(String id) {
        if(!articleRepository.existsById(id)) throw new ArticleNotFoundException("No article with id: " + id + " found to delete, sorry!");
        articleRepository.deleteById(id);
    }
}
