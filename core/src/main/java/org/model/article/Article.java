package org.model.article;

import org.model.article.code.ArticleCode;
import org.model.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Article {
    @Id
    private String name;
    private LocalDate creationDate;
    private String preview;

    @OneToMany(cascade = ALL)
    private Set<Comment> comments;

    @OneToOne(cascade = ALL)
    private ArticleCode articleCode;

    // for hibernate use only
    public Article() {}

    public Article(String name, String preview, ArticleCode articleCode) {
        this.name = name;
        this.preview = preview;
        this.articleCode = articleCode;
        creationDate = LocalDate.now();
        comments = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public ArticleCode getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(ArticleCode articleCode) {
        this.articleCode = articleCode;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
