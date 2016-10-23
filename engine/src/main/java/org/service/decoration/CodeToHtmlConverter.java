package org.service.decoration;

import org.model.article.code.SingleClass;
import org.service.decoration.annotations.HtmlElement;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.helpers.ClassContextConverter;
import org.service.decoration.helpers.CodeSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CodeToHtmlConverter {

    private final ClassContextConverter converter;

    private final CodeSplitter codeSplitter;

    @Autowired
    public CodeToHtmlConverter(ClassContextConverter converter, CodeSplitter codeSplitter) {
        this.converter = converter;
        this.codeSplitter = codeSplitter;
    }

    public SingleClass convert(String code) {
        ClassContext<CodeElement> classContext = converter.convert(new ClassContext<>(codeSplitter.split(code)));

        StringBuilder stringBuilder = new StringBuilder();

        while (classContext.hasNext()) {
            stringBuilder.append(decorateElement(classContext.next()));
        }

        return new SingleClass(stringBuilder.toString());
    }

    private String decorateElement(CodeElement element) {
        if (!element.getClass().isAnnotationPresent(HtmlElement.class)) {
            return element.getValue();
        }

        HtmlElement htmlElementAnnotation = element.getClass().getAnnotation(HtmlElement.class);

        final String htmlClassName = htmlElementAnnotation.className();
        final String displayValue = htmlElementAnnotation.displayValue();
        final String htmlDisplayValue = displayValue.isEmpty() ? element.getValue() : displayValue;

        return "<div class='" + htmlClassName + " code-element" + "'>" + htmlDisplayValue + "</div>";
    }

}
