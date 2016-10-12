package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.Number;
import org.springframework.stereotype.Component;

import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.*;

@Component
public class NumberInspection implements CodeElementInspection {
    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        if (state != State.IN_COMMENT && word.matches("[0-9]+")) {
            return new Number(word);
        }

        return NO_ELEMENT;
    }
}
