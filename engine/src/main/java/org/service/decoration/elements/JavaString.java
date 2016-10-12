package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "string")
public class JavaString extends CodeElement{
    public JavaString(String value) {
        super(value);
    }
}
