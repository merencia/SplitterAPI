package org.jsplitter.core.collection;

public interface JSIterator<T> {
    public void first();
    public void next();
    public boolean isDone();
    public T getCurrent();
}
