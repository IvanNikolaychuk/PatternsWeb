package org.controllers;

import org.model.article.code.SingleClass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

    @RequestMapping("/articles")
    public ModelAndView getA() {

        return new ModelAndView( "hello" );
    }

    @RequestMapping("/data")
    @ResponseBody
    public String get() {
        return "data";
    }

    @RequestMapping("test")
    public ModelAndView get2(@ModelAttribute("code") SingleClass singleClass) {

        return new ModelAndView( "hello" );
    }

}
