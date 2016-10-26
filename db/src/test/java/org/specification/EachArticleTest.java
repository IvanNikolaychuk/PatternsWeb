package org.specification;

import org.helpers.ArticleGenerator;
import org.hibernate.FetchMode;
import org.junit.Assert;
import org.junit.Test;
import org.repo.ArticleRepositoryBaseTest;
import org.repo.specification.EachArticle;

import static org.junit.Assert.assertEquals;

public class EachArticleTest extends ArticleRepositoryBaseTest {

    @Test
    public void allArticlesAreReturnedWhenUsingEachArticleSpec() {
        addArticles(10);
        assertEquals(articleRepository.get(new EachArticle(), FetchMode.SELECT).size(), 10);
    }

    private void addArticles(int numberOfArticles) {
        for (int i = 0; i < numberOfArticles; i++) {
            articleRepository.save(new ArticleGenerator().mockArticle());
        }
    }
}
