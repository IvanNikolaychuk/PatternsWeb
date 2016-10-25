package org.service;

import org.exceptions.NoSuchArticleException;
import org.hibernate.cfg.Configuration;
import org.model.Comment;
import org.model.article.Article;
import org.model.article.Tag;
import org.model.article.code.ArticleCode;
import org.model.article.code.ClassSection;
import org.model.article.code.SingleClass;
import org.repo.ArticleRepository;
import org.repo.specification.ByArticleName;
import org.repo.specification.ByTag;
import org.repo.specification.EachArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.FetchMode.SELECT;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }


    public void save(Article article) {
        repository.save(article);
    }

    public List<Article> getByTag(String tagName) {
        return repository.get(new ByTag(tagName), SELECT);
    }


    private Article get(String articleName, boolean thin) throws NoSuchArticleException {
        List<Article> articles = repository.get(new ByArticleName(articleName), thin ? SELECT : JOIN);

        if (articles.isEmpty()) {
            throw new NoSuchArticleException(articleName);
        }

        return articles.get(0);
    }

    private List<Article> getAll(boolean thin) {
        return repository.get( new EachArticle(), thin ? SELECT : JOIN);
    }

    public List<Article> getAll() {
        return getAll(true);
    }

    public Article get(String articleName) throws NoSuchArticleException {
        return get(articleName, true);
    }

    public Article load(String articleName) throws NoSuchArticleException {
        return get(articleName, false);
    }

}
