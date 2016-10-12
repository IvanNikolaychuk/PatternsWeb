package org.service.decoration.elements;

public abstract class CodeElement {
    public static final CodeElement NO_ELEMENT = new NoElement();

    private String value;

    public CodeElement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
