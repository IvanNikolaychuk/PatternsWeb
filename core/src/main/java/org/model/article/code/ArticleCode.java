package org.model.article.code;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static javax.persistence.CascadeType.ALL;

/**
 * Code of article. Code can be represented as {@link SingleClass}'s and {@link ClassSection}'s.
 * Usually article has both {@link SingleClass}'s and {@link ClassSection}'s in.
 */
@Entity
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class ArticleCode {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(cascade = ALL)
    private Set<SingleClass> classes;

    @OneToMany(cascade = ALL)
    private Set<ClassSection> sections;

    // for hibernate use only
    public ArticleCode() {}

    public ArticleCode(Set<SingleClass> classes, Set<ClassSection> sections) {
        this.classes = classes;
        this.sections = sections;
    }

}
