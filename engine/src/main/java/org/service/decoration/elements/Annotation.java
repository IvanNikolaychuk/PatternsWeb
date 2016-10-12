package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "annotation")
public class Annotation extends CodeElement {
    public Annotation(String word) {
        super(word);
    }
}
