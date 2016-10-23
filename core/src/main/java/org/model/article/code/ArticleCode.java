package org.model.article.code;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * Code of article. Code can be represented as {@link SingleClass}'s and {@link ClassSection}'s.
 * Usually article has both {@link SingleClass}'s and {@link ClassSection}'s in.
 */
@Entity
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
