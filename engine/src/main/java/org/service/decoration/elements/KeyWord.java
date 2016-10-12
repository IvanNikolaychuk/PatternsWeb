package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "key-word")
public class KeyWord extends CodeElement {
    public KeyWord(String value) {
        super(value);
    }
}
