package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;

    private String author;
    private String text;
    private LocalDate creationDate;

    // for hibernate use only
    public Comment(){}

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
        creationDate = LocalDate.now();
    }
}
