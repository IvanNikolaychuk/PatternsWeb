package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;

import static org.service.decoration.helpers.ClassContextConverter.State;

/**
 * Inspection is responsible for defining what type of element given word refers too.
 * It is done depending on given state and ClassContextCopy.
 */
public interface CodeElementInspection {
    CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy);
}
