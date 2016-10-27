package org.service;

import org.model.article.Article;
import org.repo.ArticleRepository;
import org.repo.specification.ByArticleName;
import org.repo.specification.ByTag;
import org.repo.specification.EachArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    private Optional<Article> get(String articleName, boolean thin)  {
        List<Article> articles = repository.get(new ByArticleName(articleName), thin ? SELECT : JOIN);

        return articles.isEmpty() ?
                Optional.empty() :
                Optional.of(articles.get(0));
    }

    public List<Article> getAll() {
        return repository.get( new EachArticle(), SELECT);
    }

    public Optional<Article> get(String articleName)  {
        return get(articleName, true);
    }

    public Optional<Article> load(String articleName)  {
        return get(articleName, false);
    }

}
