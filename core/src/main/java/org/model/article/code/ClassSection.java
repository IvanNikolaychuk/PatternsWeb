package org.model.article.code;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.persistence.CascadeType.ALL;

/**
 * ClassSection is simply a better abstraction for a List of SingleClass'es.
 * On UI this is a monolith block where {@link SingleClass} act as tabs.
 */
@Entity
public class ClassSection {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(cascade = ALL)
    private final List<SingleClass> singleClasses;

    public ClassSection() {
        this.singleClasses = new ArrayList<>();
    }

    public ClassSection(SingleClass... singleClasses) {
        this.singleClasses = new ArrayList<>();
        this.singleClasses.addAll(asList(singleClasses));
    }
}
