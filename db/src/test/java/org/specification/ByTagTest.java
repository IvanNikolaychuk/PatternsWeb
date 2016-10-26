package org.specification;

import org.helpers.ArticleGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.repo.ArticleRepositoryBaseTest;
import org.repo.specification.ByTag;

import static org.hibernate.FetchMode.SELECT;
import static org.junit.Assert.assertEquals;

public class ByTagTest extends ArticleRepositoryBaseTest {
    private static final String TAG_1 = "tag1";
    private static final String TAG_2 = "tag2";

    @Test
    public void onlyArticlesWithSearchedTagsAreReturned() {
        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleRepository.save(articleGenerator.mockArticle(TAG_1, TAG_2));
        articleRepository.save(articleGenerator.mockArticle(TAG_1));
        articleRepository.save(articleGenerator.mockArticle(TAG_2));

        assertEquals(articleRepository.get(new ByTag(TAG_1), SELECT).size(), 2);
        assertEquals(articleRepository.get(new ByTag(TAG_2), SELECT).size(), 2);
    }

}
