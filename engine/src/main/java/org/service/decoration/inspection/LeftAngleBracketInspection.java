package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.LeftAngleBracketElement;
import org.service.decoration.helpers.ClassContextConverter;
import org.springframework.stereotype.Component;

@Component
public class LeftAngleBracketInspection implements CodeElementInspection {
    @Override
    public CodeElement inspect(String word, ClassContextConverter.State state, ClassContext<String> evaluationCopy) {
        if (word.equals("<")) {
            return new LeftAngleBracketElement(word);
        }

        return CodeElement.NO_ELEMENT;
    }
}
