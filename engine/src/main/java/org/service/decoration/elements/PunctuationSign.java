package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

@HtmlElement(className = "punctuation-sign")
public class PunctuationSign extends CodeElement {
    public PunctuationSign(String value) {
        super(value);
    }
}
