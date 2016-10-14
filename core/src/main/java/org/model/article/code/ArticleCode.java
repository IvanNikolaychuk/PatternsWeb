package org.model.article.code;


import javax.persistence.*;
import java.util.List;

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
    private List<SingleClass> classes;

    @OneToMany(cascade = ALL)
    private List<ClassSection> sections;

    // for hibernate use only
    public ArticleCode() {}

    public ArticleCode(List<SingleClass> classes, List<ClassSection> sections) {
        this.classes = classes;
        this.sections = sections;
    }

    public List<SingleClass> getClasses() {
        return classes;
    }

    public List<ClassSection> getSections() {
        return sections;
    }
}
