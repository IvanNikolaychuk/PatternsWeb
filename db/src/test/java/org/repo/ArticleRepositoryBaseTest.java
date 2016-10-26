package org.repo;

import org.helpers.ArticleGenerator;
import org.hibernate.FetchMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.model.article.Article;
import org.repo.specification.EachArticle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hibernate.FetchMode.SELECT;
import static org.junit.Assert.assertEquals;

public class ArticleRepositoryBaseTest {
    protected static ArticleRepository articleRepository;
    private ArticleGenerator articleGenerator;

    @Before
    public void init() {
        for (Article article : articleRepository.get(new EachArticle(), FetchMode.SELECT)) {
            articleRepository.delete(article);
        }
        checkRepositoryIsEmpty();

        articleGenerator = new ArticleGenerator();
    }

    @BeforeClass
    public static void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("db-module-test-context.xml");
        articleRepository = context.getBean(ArticleRepository.class);
    }


    public void checkRepositorySize(int expectedSize) {
        assertEquals(getRepositorySize(), expectedSize);
    }

    public void checkRepositoryIsEmpty() {
        checkRepositorySize(0);
    }

    private int getRepositorySize() {
        return articleRepository.get(new EachArticle(), FetchMode.SELECT).size();
    }

    private void checkRepositoryIsNotEmpty() {
        Assert.assertTrue(getRepositorySize() > 0);
    }

    void generateArticles(int numberOfArticles) {
        for (int i = 0; i < numberOfArticles; i++) {
            articleRepository.save(articleGenerator.mockArticle());
        }
    }

    public Article firstArticleInRepo() {
        checkRepositoryIsNotEmpty();
        return articleRepository.get(new EachArticle(), SELECT).get(0);
    }


}
