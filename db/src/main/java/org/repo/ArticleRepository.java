package org.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.model.article.Article;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleRepository  {
    private final SessionFactory sessionFactory;

    @Autowired
    public ArticleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Article article) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
        }
        }
}
