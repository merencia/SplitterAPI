package org.jsplitter.core.collection;

public interface JSList<T> {

    public JSIterator<T> createIterator();

    public void add(T element);

    public void remove(T element);

    public int size();

    public T get(int index);

    public void add(int index, T element);
}
