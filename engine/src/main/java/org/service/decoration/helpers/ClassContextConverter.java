package org.service.decoration.helpers;

import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.inspection.CodeElementInspection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClassContextConverter {
    @Autowired
    @Qualifier("compositeInspector")
    private CodeElementInspection inspector;

    @Autowired
    private StateManagerFactory stateManagerFactory;

    public ClassContext<CodeElement> convert(ClassContext<String> classContext) {
        StateManager stateManager = stateManagerFactory.createStateManager();
        CodeElement[] elements = new CodeElement[classContext.numberOfElements()];

        int index = 0;
        while (classContext.hasNext()) {
            final String word = classContext.next();
            elements[index++] = inspector.inspect(word, stateManager.getActualState(word), classContext.evaluationCopy());
        }

        return new ClassContext<>(elements);
    }


    /**
     * State of class in the process of convection. Some states intercept here (this is done for more convenient use).
     * For example, is we have {@link State#IN_CLASS} this means that we are in class, but not in method. Otherwise
     * we would have {@link State#IN_METHOD} state.
     */
    public enum State {
        // imports
        BEFORE_CLASS,
        IN_METHOD,
        IN_CLASS,
        IN_COMMENT,
    }
}
