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
}
