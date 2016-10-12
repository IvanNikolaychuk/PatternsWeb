package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "digit")
public class Number extends CodeElement {
    public Number(String value) {
        super(value);
    }
}
