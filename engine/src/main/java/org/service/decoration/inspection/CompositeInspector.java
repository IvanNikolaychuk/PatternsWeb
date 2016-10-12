package org.service.decoration.inspection;

import org.service.decoration.helpers.ClassContextConverter.State;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.GenericElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("compositeInspector")
public class CompositeInspector implements CodeElementInspection {

    private final List<CodeElementInspection> inspections;

    @Autowired
    public CompositeInspector(List<CodeElementInspection> inspections) {
        this.inspections = inspections;
    }

    @Override
    public CodeElement inspect(String word, State state, ClassContext<String> evaluationCopy) {
        for (CodeElementInspection inspection : inspections) {
            CodeElement element = inspection.inspect(word, state, evaluationCopy);
            if (element != CodeElement.NO_ELEMENT) {
                return element;
            }
        }

        return new GenericElement(word);
    }
}
