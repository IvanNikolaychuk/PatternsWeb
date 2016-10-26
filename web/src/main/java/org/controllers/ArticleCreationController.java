package org.controllers;

import org.service.console.article.creation.ArticleCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class ArticleCreationController {
    private ArticleCreationService articleCreationService;

    @Autowired
    public ArticleCreationController(ArticleCreationService articleCreationService) {
        this.articleCreationService = articleCreationService;
    }

    @RequestMapping(value = "/articles/new", method = RequestMethod.GET)
    public ModelAndView get() {
        return new ModelAndView("article-creation");
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public ModelAndView get(@RequestParam("article-name") String articleName,
                            @RequestParam("preview") String preview,
                            @RequestParam("tags") String tags,
                            @RequestParam("path-to-dir-with-classes") String pathToDirWithClasses) throws Exception {
        articleCreationService.create(articleName, preview,
                new HashSet<>(Arrays.asList(tags.split(","))), pathToDirWithClasses);

         return new ModelAndView(articleName.toLowerCase());
    }
}
