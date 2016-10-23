package org.repo;

import org.hibernate.*;
import org.model.article.Article;
import org.repo.specification.ArticleSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;

public class ArticleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ArticleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Article article) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
        }
    }

    @SuppressWarnings("all")
    public List<Article> get(ArticleSpecification specification, FetchMode fetchMode) {
        try(Session session = sessionFactory.openSession()) {

            return buildCriteria(session, fetchMode)
                    .add(specification.toCriteria())
                    .list();
        }
    }

    private Criteria buildCriteria(Session session, FetchMode fetchMode) {
        return session
                .createCriteria(Article.class)
                .setFetchMode("comments", fetchMode)
                .setFetchMode("articleCode.classes", fetchMode)
                .setFetchMode("articleCode.sections", fetchMode)
                .setResultTransformer(DISTINCT_ROOT_ENTITY);
    }
}
