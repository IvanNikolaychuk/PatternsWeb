package org.repo;

import org.helpers.ArticleGenerator;
import org.junit.Test;

public class ArticleRepositoryTest extends ArticleRepositoryBaseTest {

    @Test
    public void articleIsSuccessfullyAdded() {
        articleRepository.save(new ArticleGenerator().mockArticle());
        checkRepositorySize(1);
    }

    @Test
    public void articleIsSuccessfullyDeleted() {
        generateArticles(1);
        articleRepository.delete(firstArticleInRepo());
        checkRepositoryIsEmpty();
    }

}
