package org.service;

import org.model.article.Article;
import org.model.article.code.ArticleCode;
import org.model.article.code.ClassSection;
import org.model.article.code.SingleClass;
import org.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    public void save() {
        SingleClass singleClass1 = new SingleClass("singletonClass1");
        List<ClassSection> classSections = new ArrayList<>();
        classSections.add(new ClassSection(new SingleClass("singletonClass2"), new SingleClass("singletonClass3")));
        classSections.add(new ClassSection(new SingleClass("singletonClass4"), new SingleClass("singletonClass5")));
        ArticleCode articleCode = new ArticleCode(singletonList(singleClass1), classSections);
        Article article = new Article("name", "preview", articleCode);


        repository.save(article);
    }
}
