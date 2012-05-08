package org.jsplitter.visitor;

/**
 *
 * @author Lucas
 */
public interface Visitor<T> {

    public void visit(T element);
}
