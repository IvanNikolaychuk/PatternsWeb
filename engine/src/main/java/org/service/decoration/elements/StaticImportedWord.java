package org.service.decoration.elements;

import org.service.decoration.annotations.HtmlElement;

// static imported words are displayed as class fields
@HtmlElement(className = "class-field")
public class StaticImportedWord extends CodeElement {
    public StaticImportedWord(String value) {
        super(value);
    }
}
