package me.danilomarchesani.openwikipedia.service;

import me.danilomarchesani.openwikipedia.errors.ArticleHistoryNotFoundException;
import me.danilomarchesani.openwikipedia.model.Article;
import me.danilomarchesani.openwikipedia.model.ArticleHistory;
import me.danilomarchesani.openwikipedia.repository.ArticleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleHistoryService {

    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;

    public Set<ArticleHistory> getArticleHistory(Article article) {
        if (!articleHistoryRepository.existsById(article.getId()))
            throw new ArticleHistoryNotFoundException("History of the article: " + article.getTitle() + " not found or possible never existed an history of this article.");
        return articleHistoryRepository.findByArticle(article);
    }

    /**
     * This method is used to save a snapshot of an article before it gets updated.
     *
     * @param article
     * @return articleHistory snapshot
     * @author Danilo M. 31/05/2024
     */
    public ArticleHistory createArticleHistory(Article article) throws Exception {
        try {
            ArticleHistory snapshot = new ArticleHistory();
            snapshot.setArticle(article);
            articleHistoryRepository.save(snapshot);
            return snapshot;
        } catch (Exception e) {
            throw new Exception("Error while creating the snapshot of the article: " + e.getMessage());
        }
    }

    /**
     * THis method deletes a specific History snapshot of an article.
     *
     * @param id
     * @author Danilo M. 31/05/2024
     */
    public void deleteAnArticleHistory(String id) {
        if (!articleHistoryRepository.existsById(id))
            throw new ArticleHistoryNotFoundException("Sorry but seems that the history of the article that you are trying to delete doesn't exist.");
        articleHistoryRepository.deleteById(id);
    }

    /**
     * THis method deletes all History snapshots of an article.
     *
     * @param ids
     * @author Danilo M. 31/05/2024
     */
    public void deleteAllArticleHisotries(List<String> ids) throws Exception {
        try {
            articleHistoryRepository.deleteAllById(ids);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}