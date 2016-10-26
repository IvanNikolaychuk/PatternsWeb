package org.controllers;

import org.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/{articleName}")
    public ModelAndView get(@PathVariable String articleName) {
        return articleService.get(articleName).isPresent() ?
                new ModelAndView("articles/" + articleName) :
                new ModelAndView("concrete");
    }

}
