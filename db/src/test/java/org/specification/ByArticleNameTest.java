package org.specification;

import org.helpers.ArticleGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.repo.ArticleRepositoryBaseTest;

public class ByArticleNameTest extends ArticleRepositoryBaseTest {

    @Test
    public void articleWithSearchedNameCanBeObtainedWhenExists() {
        articleRepository.save(new ArticleGenerator().createArticle("name"));
        checkRepositorySize(1);
        Assert.assertEquals(firstArticleInRepo().getName(), "name");
    }
}
