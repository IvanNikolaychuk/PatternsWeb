package org.repo.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ByArticleName implements ArticleSpecification {
    private final String articleName;

    public ByArticleName(String name) {
        this.articleName = name;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("name", articleName);
    }
}
