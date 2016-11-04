package org.service.decoration.context;

/**
 * Represents the whole content of the class with convenient navigation via article elements.
 */
public class ClassContext<T> {
//    private final T noElement;
    private final T[] elements;
    private int currIndex;

    public ClassContext(T[] elements) {
        this(elements, -1);
    }

    private ClassContext(T[] elements, int currIndex) {
        this.elements = elements;
        this.currIndex = currIndex;
    }

    public T next() {
       return hasNext() ?  elements[++currIndex] : null;
    }

    public boolean hasNext() {
        return currIndex + 1 < elements.length;
    }

    public int size() {
        return elements.length;
    }

    public ClassContext<T> evaluationCopy() {
        return new ClassContext(elements, currIndex);
    }
}
