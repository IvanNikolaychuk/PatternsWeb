package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "angel-bracket", displayValue = "&lt;")
public class LeftAngleBracketElement extends CodeElement{
    public LeftAngleBracketElement(String value) {
        super(value);
    }
}
