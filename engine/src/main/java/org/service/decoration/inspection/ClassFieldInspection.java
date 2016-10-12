package org.service.decoration.inspection;

import org.service.decoration.helpers.ClassContextConverter;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.ClassField;
import org.service.decoration.elements.CodeElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.service.decoration.helpers.ClassContextConverter.State.*;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;

@Component
public class ClassFieldInspection implements CodeElementInspection {
    private final List<String> classFields;

    public ClassFieldInspection() {
        this.classFields = new ArrayList<>();
    }

    @Override
    public CodeElement inspect(String word, ClassContextConverter.State state, ClassContext<String> evaluationCopy) {
        if (state == IN_COMMENT) {
            return NO_ELEMENT;
        } else if (state == IN_CLASS && isClassVariableDeclaration(word, evaluationCopy)) {
                classFields.add(word);
                return new ClassField(word);
        } else if (state == IN_METHOD && classFields.contains(word)) {
            return new ClassField(word);
        }

        return NO_ELEMENT;
    }

    private boolean isClassVariableDeclaration(String word, ClassContext<String> evaluationCopy) {
        if (!word.matches("[a-zA-Z_]+")) {
            return false;
        }

        String nextNotBlankWord = evaluationCopy.next();
        while (nextNotBlankWord.trim().isEmpty()) {
            nextNotBlankWord = evaluationCopy.next();
        }

        return nextNotBlankWord.equals(";") || nextNotBlankWord.equals("=");
    }
}
