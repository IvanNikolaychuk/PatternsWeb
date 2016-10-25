package org.repo.specification;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class EachArticle extends ArticleSpecification {

    @Override
    public Criteria toCriteria(Session session) {
        return buildCriteria(session);
    }
}
