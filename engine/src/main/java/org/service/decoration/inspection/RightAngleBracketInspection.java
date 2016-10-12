package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.RightAngleBracket;
import org.service.decoration.helpers.ClassContextConverter;
import org.springframework.stereotype.Component;

@Component
public class RightAngleBracketInspection implements CodeElementInspection {
    @Override
    public CodeElement inspect(String word, ClassContextConverter.State state, ClassContext<String> evaluationCopy) {
        if (word.equals(">")) {
            return new RightAngleBracket(word);
        }

        return CodeElement.NO_ELEMENT;
    }
}
