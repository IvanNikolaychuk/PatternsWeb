package org.service.decoration.inspection;

import org.service.decoration.context.ClassContext;

public class InspectionHelper {

    public static ClassContext<String> classContext(String... elements) {
        return new ClassContext<>(elements);
    }
}
