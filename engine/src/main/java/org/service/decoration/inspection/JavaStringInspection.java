package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.JavaString;
import org.springframework.stereotype.Component;

import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.State;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;

@Component
public class JavaStringInspection implements CodeElementInspection {
    private boolean inStringNow;

    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        if (state == IN_COMMENT) {
            return NO_ELEMENT;
        }

        if (inStringNow) {
            if (word.equals("\"")) {
                inStringNow = false;
            }

            return new JavaString(word);
        } else if (word.equals("\"")) {
            inStringNow = true;

            return new JavaString(word);
        }

        return NO_ELEMENT;
    }
}
