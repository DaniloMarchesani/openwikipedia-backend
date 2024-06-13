package me.danilomarchesani.openwikipedia.service;

import me.danilomarchesani.openwikipedia.errors.ArticleNotFoundException;
import me.danilomarchesani.openwikipedia.errors.UserNotFoundException;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.model.User;
import me.danilomarchesani.openwikipedia.repository.ArticleRepository;
import me.danilomarchesani.openwikipedia.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    public List<Article> getAllArticlesOfUsername(String username) throws Exception {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            if(!user.isPresent()) throw new UserNotFoundException("User with username: " + username + " not found!");
            Optional<List<Article>> articles = articleRepository.findByUserId(user.get().getId());
            if(!articles.isPresent()) throw new ArticleNotFoundException("Articles of user: " + username + " not found any!");
            return articles.get();
        } catch (Exception e) {
            throw new Exception("Error occurred while fetching all user articles: " + e.getMessage());
        }
    }

    public Article getArticleByTitle(String articleTitle) throws Exception {
        try{
            Optional<Article> article = articleRepository.findByTitle(articleTitle);
            if(!article.isPresent()) throw new ArticleNotFoundException("Article with title: " + articleTitle + " Not found!");
            return article.get();
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    public Stream<Article> getAllArticlesByTitle(String articleTitle) throws Exception {
        try {
            Set<Article> articles = articleRepository.findManyArticlesByTitle(articleTitle);
            return articles.stream();
        } catch (Exception e) {
            throw new Exception("Error occurred while getting back the articles: " + e.getMessage());
        }
    }

    public Article save(Article article) throws Exception {
        try {
            return articleRepository.save(article);
        } catch (Exception e) {
            throw new Exception("Error occurred while saving the article: " + e.getMessage());
        }
    }

    public List<Article> saveMultiple(List<Article> articles) throws Exception {
        try {
            return articleRepository.saveAll(articles);
        } catch (Exception e) {
            throw new Exception("Error occurred while saving the articles: " + e.getMessage());
        }
    }

    public Article updateArticle(String id, Article articleUpdated) throws Exception {
        try {
            if(!articleRepository.existsById(id)) throw new ArticleNotFoundException("Article to update with title: " + articleUpdated.getTitle() + " not found.");

            Article article = new Article();
            article.setId(articleUpdated.getId());
            article.setTitle(articleUpdated.getTitle());
            article.setSavedOnDate(new Date());
            article.setContent(articleUpdated.getContent());
            article.setLastModifiedDate(new Date());

            articleRepository.save(article);

            return article;
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    public void deleteArticle(String id) throws Exception {
        try {
            if(!articleRepository.existsById(id)) throw new ArticleNotFoundException("No article with id: " + id + " found to delete, sorry!");
            articleRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

    public Article getArticleById(String id) throws Exception {
        try{
            Optional<Article> article = articleRepository.findById(id);
            if(!article.isPresent()) throw new ArticleNotFoundException("Article with id: " + id + " not found");
            return article.get();
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }
}
