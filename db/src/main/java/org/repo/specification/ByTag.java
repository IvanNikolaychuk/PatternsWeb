package org.repo.specification;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ByTag extends ArticleSpecification {
    private String tagName;

    public ByTag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public Criteria toCriteria(Session session) {
        return buildCriteria(session)
                .createCriteria("tags")
                    .add(Restrictions.eq("name", tagName).ignoreCase());
    }
}
