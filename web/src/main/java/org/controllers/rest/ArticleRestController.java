package org.controllers.rest;

import org.model.article.Article;
import org.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                               String thin)  {
        // TODO: Make 404 page in case no such article
        return thin.equals("true") ?
                        articleService.get(articleName).get() :
                        articleService.load(articleName).get();
    }

    @RequestMapping("/articles")
    public List<Article> get(@RequestParam(value = "tag", defaultValue = "")
                                         String tag) {
        return tag.isEmpty() ?
                articleService.getAll() :
                articleService.getByTag(tag);
    }
}
