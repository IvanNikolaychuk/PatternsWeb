package org.repo.specification;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ByArticleName extends ArticleSpecification {
    private String articleName;

    public ByArticleName(String articleName) {
        super();
        this.articleName = articleName;
    }

    @Override
    public Criteria toCriteria(Session session) {
        return buildCriteria(session)
                .add(Restrictions.eq("name", articleName));
    }

}
