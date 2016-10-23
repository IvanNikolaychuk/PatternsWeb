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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        return creationDate != null ? creationDate.equals(comment.creationDate) : comment.creationDate == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
