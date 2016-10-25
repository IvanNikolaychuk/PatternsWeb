package org.controllers;

import org.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/article/save")
    public void save() {
        articleService.save();
    }

    @RequestMapping("/articles/new")
    public ModelAndView getArticleCreationForm() {
        return new ModelAndView("article-creation");
    }


    @RequestMapping("/concrete")
    public ModelAndView get() {
        return new ModelAndView("concrete");
    }

}
