package org.repo.specification;

import org.hibernate.criterion.Criterion;

public interface ArticleSpecification {
    Criterion toCriteria();
}
