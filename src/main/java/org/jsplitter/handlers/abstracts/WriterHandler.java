package org.jsplitter.handlers.abstracts;

import java.io.IOException;
import org.jsplitter.exception.NotDirectoryException;
import org.jsplitter.handlers.interfaces.Handler;

/*
 * Padrões: Chain, Proxy
 */
/**
 *
 * @author Lucas
 */
public abstract class WriterHandler implements Handler<WriterHandler> {
    private WriterHandler nextHandler;

    @Override
    public void setNextHandler(WriterHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public WriterHandler getNextHandler() {
        return nextHandler;
    }
    

    public abstract void handleRequets(byte[] bytes) throws SecurityException, IOException, NotDirectoryException;
}
