package org.model.article;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.model.Comment;
import org.model.article.code.ArticleCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;
import static javax.persistence.CascadeType.ALL;

@Entity
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
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

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
