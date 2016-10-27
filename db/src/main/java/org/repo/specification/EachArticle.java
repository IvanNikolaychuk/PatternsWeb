package org.repo.specification;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class EachArticle extends ArticleSpecification {

    @Override
    public Criteria toCriteria(Session session) {
        return buildCriteria(session)
                .addOrder(Order.desc("creationDate"));
    }
}
