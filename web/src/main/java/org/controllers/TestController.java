package org.controllers;

import org.service.ArticleService;
import org.service.decoration.CodeToHtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TestController {

    @Autowired
    private CodeToHtmlConverter codeDecoratorService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/hello")
    public ModelAndView getView() {
        articleService.save();
        return new ModelAndView( "hello" );
    }

    @RequestMapping(value = "/testCodeService", method = POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public void test(@RequestParam String code) {
        codeDecoratorService.convert(code);
    }
}
