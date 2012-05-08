package org.jsplitter.builder.impl;

import java.util.ArrayList;
import java.util.List;
import org.jsplitter.builder.interfaces.Builder;
import org.jsplitter.handlers.interfaces.Handler;

/*
 * Padrões: Chain, Builder
 */

public class ChainBuilder<T extends Handler> implements Builder<T> {
    
    private List<T> handlers = null;

    public ChainBuilder() {
        handlers = new ArrayList<T>();
    }

    public Builder<T> addNext(T next) {
        handlers.add(next);
        return this;
    }

    @Override
    public T build() {
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNextHandler(handlers.get(i + 1));
        }
        return handlers.isEmpty() ? null : handlers.get(0);
    }
    
}
