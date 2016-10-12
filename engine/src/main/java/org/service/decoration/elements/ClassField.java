package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "class-field")
public class ClassField extends CodeElement {
    public ClassField(String word) {
        super(word);
    }
}
