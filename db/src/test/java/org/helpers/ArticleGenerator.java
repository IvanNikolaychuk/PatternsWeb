package org.helpers;

import org.model.article.Article;
import org.model.article.Tag;
import org.model.article.code.ArticleCode;
import org.model.article.code.ClassSection;
import org.model.article.code.SingleClass;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.util.Collections.singleton;

public class ArticleGenerator {
    private Random random;

    public ArticleGenerator() {
        random = new Random();
    }

    public Article mockArticle() {
        return createArticle(random.nextInt() + "");
    }

    public Article mockArticle(String... tagNames) {

        Set<Tag> tags = new HashSet<>();

        for (String tagName : tagNames) {
            tags.add(new Tag(tagName));
        }

        return createArticle(random.nextInt() + "", tags);
    }

    public Article createArticle(String name) {
        return createArticle(name, new HashSet<>());
    }

    public Article createArticle(String name, Set<Tag> tags) {
        return new Article(name, "preview",
                new ArticleCode(singleton(new SingleClass()), singleton(new ClassSection())), tags);
    }


}
