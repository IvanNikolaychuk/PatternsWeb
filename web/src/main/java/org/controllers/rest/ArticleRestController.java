package org.controllers.rest;

import org.exceptions.NoSuchArticleException;
import org.model.article.Article;
import org.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/")
public class ArticleRestController {
    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/articles/{articleName}")
    public Article get(@PathVariable String articleName,
                       @RequestParam(value = "thin", defaultValue = "false")
                               String thin) throws NoSuchArticleException {
        return thin.equals("true") ?
                        articleService.get(articleName) :
                        articleService.load(articleName);
    }
}
