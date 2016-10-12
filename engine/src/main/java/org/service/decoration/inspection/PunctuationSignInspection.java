package org.service.decoration.inspection;

import org.service.decoration.helpers.ClassContextConverter.State;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.PunctuationSign;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;

@Component
public class PunctuationSignInspection implements CodeElementInspection {
    private static final List<String> PUNCTUATION_SIGNS = Arrays.asList(";", ".");

    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        if (state == IN_COMMENT || !PUNCTUATION_SIGNS.contains(word)) {
            return NO_ELEMENT;
        }

        return new PunctuationSign(word);
    }
}
