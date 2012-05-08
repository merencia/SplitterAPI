package org.jsplitter.handlers.interfaces;

/*
 * Padrões: Chain
 */
public interface Handler<T> {

    public void setNextHandler(T nextHandler);

    public T getNextHandler();
}
