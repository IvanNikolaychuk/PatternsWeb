package org.repo.specification;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.model.article.Article;

import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.FetchMode.SELECT;
import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;

public abstract class ArticleSpecification {
    private FetchMode fetchMode;

    public ArticleSpecification() {
        this.fetchMode = SELECT;
    }

    public abstract Criteria toCriteria(Session session);

    public ArticleSpecification fetchMode(FetchMode fetchMode) {
        this.fetchMode = fetchMode;
        return this;
    }

    Criteria buildCriteria(Session session) {
        return session
                .createCriteria(Article.class)
                .setFetchMode("comments", fetchMode)
                .setFetchMode("articleCode.classes", fetchMode)
                .setFetchMode("articleCode.sections", fetchMode)
                .setResultTransformer(DISTINCT_ROOT_ENTITY);
    }
}
