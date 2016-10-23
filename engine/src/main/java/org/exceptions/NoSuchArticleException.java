package org.exceptions;

public class NoSuchArticleException extends Exception {

    public NoSuchArticleException(String articleName) {
       super(articleName);
    }
}
