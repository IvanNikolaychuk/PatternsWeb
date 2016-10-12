package org.service.decoration.inspection;

import org.service.decoration.helpers.ClassContextConverter;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.Comment;
import org.springframework.stereotype.Component;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;

@Component
public class CommentInspection implements CodeElementInspection {

    @Override
    public CodeElement inspect(String word, ClassContextConverter.State state, ClassContext<String> evaluationCopy) {
        if (state != IN_COMMENT || isEmptyWord(word)) {
            return NO_ELEMENT;
        }

        return new Comment(word);
    }

    private boolean isEmptyWord(String word) {
        return word.trim().length() == 0;
    }
}
