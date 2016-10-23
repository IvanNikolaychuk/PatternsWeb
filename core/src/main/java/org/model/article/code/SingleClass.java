package org.model.article.code;

import javax.persistence.*;

@Entity
public class SingleClass {
    @Id
    @GeneratedValue
    private int id;

    @Access(AccessType.FIELD)
    private String code;

    public SingleClass() {
    }

    public SingleClass(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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
