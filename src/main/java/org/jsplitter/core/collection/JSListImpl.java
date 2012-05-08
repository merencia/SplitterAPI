package org.jsplitter.core.collection;

import java.util.ArrayList;
import java.util.List;

public class JSListImpl<T> implements JSList<T> {

    private List<T> elements = new ArrayList<T>();

    @Override
    public JSIterator<T> createIterator() {
        return new JSListIterator();
    }

    @Override
    public void add(T element) {
        elements.add(element);
    }

    @Override
    public void remove(T element) {
        elements.remove(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public void add(int index, T element) {
        elements.add(index, element);
    }

    private class JSListIterator implements JSIterator<T> {

        private int atual = 0;

        @Override
        public void first() {
            atual = 0;
        }

        @Override
        public void next() {
            atual += 1;
        }

        @Override
        public boolean isDone() {
            if (atual >= size()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T getCurrent() {
            return get(atual);
        }
    }
}
