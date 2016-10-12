package org.service.decoration.inspection;

import org.service.decoration.helpers.ClassContextConverter;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.Annotation;
import org.service.decoration.elements.CodeElement;
import org.springframework.stereotype.Component;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;

@Component
public class AnnotationInspection implements CodeElementInspection {
    private static final String ANNOTATION_PREFIX = "@";

    @Override
    public CodeElement inspect(String word, ClassContextConverter.State state, ClassContext<String> evaluationCopy) {
        if (state == IN_COMMENT || !word.contains(ANNOTATION_PREFIX)) {
            return NO_ELEMENT;
        }

        return new Annotation(word);
    }
}
