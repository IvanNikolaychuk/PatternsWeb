package org.model.article.code;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@Entity
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class SingleClass {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(columnDefinition="text")
    private String code;

    public SingleClass() {
    }

    public SingleClass(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleClass that = (SingleClass) o;

        if (id != that.id) return false;
        return code != null ? code.equals(that.code) : that.code == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
