package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.StaticImportedWord;
import org.springframework.stereotype.Component;

import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.*;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;

@Component
public class StaticImportedWordInspection implements CodeElementInspection {

    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        if (state == IN_COMMENT) {
            return NO_ELEMENT;
        }

        if (looksLikeStaticImportedWord(word)) {
            return new StaticImportedWord(word);
        }

        return NO_ELEMENT;
    }

    private boolean looksLikeStaticImportedWord(String word) {
        return word.matches("[A-Z\\_]*");
    }
}
