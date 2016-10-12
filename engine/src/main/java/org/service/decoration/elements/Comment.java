package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "comment")
public class Comment extends CodeElement {
    public Comment(String value) {
        super(value);
    }
}
