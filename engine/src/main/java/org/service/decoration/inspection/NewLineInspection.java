package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.NewLineElement;
import org.springframework.stereotype.Component;

import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.*;

@Component
public class NewLineInspection implements CodeElementInspection {
    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        if (word.contains("\n") || word.contains("\r")) {
            return new NewLineElement(word);
        }

        return NO_ELEMENT;
    }
}
