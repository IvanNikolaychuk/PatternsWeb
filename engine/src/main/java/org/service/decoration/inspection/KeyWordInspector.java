package org.service.decoration.inspection;

import org.service.decoration.helpers.ClassContextConverter;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.KeyWord;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;

@Component
public class KeyWordInspector implements CodeElementInspection {
    private static final List<String> KEYWORDS = asList("abstract", "continue", "for", "new", "switch", "assert",
            "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double",
            "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum",
            "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final",
            "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
            "native", "super", "while");

    @Override
    public CodeElement inspect(String word, ClassContextConverter.State state, ClassContext<String> evaluationCopy) {
        if (state == IN_COMMENT || !KEYWORDS.contains(word)) {
            return NO_ELEMENT;
        }

        return new KeyWord(word);
    }
}
