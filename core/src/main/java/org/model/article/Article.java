package org.model.article;

import org.model.article.code.ArticleCode;
import org.model.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Article {
    @Id
    private String name;
    private LocalDate creationTime;
    private String preview;

    @OneToMany(cascade = ALL)
    private List<Comment> comments;

    @OneToOne(cascade = ALL)
    private ArticleCode articleCode;

    // for hibernate use only
    public Article() {}

    public Article(String name, String preview, ArticleCode articleCode) {
        this.name = name;
        this.preview = preview;
        this.articleCode = articleCode;
        creationTime = LocalDate.now();
        comments = new ArrayList<>();
    }
}
