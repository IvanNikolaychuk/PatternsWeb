package org.repo;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.model.article.Article;
import org.repo.specification.ArticleSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        try (Session session = sessionFactory.openSession()) {

            return specification
                    .fetchMode(fetchMode)
                    .toCriteria(session)
                    .list();
        }
    }

    public void delete(Article article) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(article);
            transaction.commit();
        }
    }
}
