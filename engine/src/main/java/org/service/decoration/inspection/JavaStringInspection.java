package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.JavaString;
import org.service.decoration.helpers.ClassContextConverter;
import org.springframework.stereotype.Component;

import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.*;
import static org.service.decoration.helpers.ClassContextConverter.State.*;

@Component
public class JavaStringInspection implements CodeElementInspection {
    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        if (state != IN_COMMENT && word.matches("\".*\"")) {
            return new JavaString(word);
        }

        return NO_ELEMENT;
    }
}
