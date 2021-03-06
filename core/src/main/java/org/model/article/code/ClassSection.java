package org.model.article.code;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static javax.persistence.CascadeType.ALL;

/**
 * ClassSection is simply a better abstraction for a List of SingleClass'es.
 * On UI this is a monolith block where {@link SingleClass} act as tabs.
 */
@Entity
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class ClassSection {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(cascade = ALL)
    private final Set<SingleClass> singleClasses;

    public ClassSection() {
        this.singleClasses = new HashSet<>();
    }

    public ClassSection(Set<SingleClass> singleClasses) {
        this.singleClasses = singleClasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassSection that = (ClassSection) o;

        if (id != that.id) return false;
        return singleClasses != null ? singleClasses.equals(that.singleClasses) : that.singleClasses == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (singleClasses != null ? singleClasses.hashCode() : 0);
        return result;
    }

}
