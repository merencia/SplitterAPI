package org.jsplitter.handlers.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.handlers.interfaces.Handler;

/*
 * Padrões: Chain, Proxy
 */
/**
 *
 * @author Lucas
 */
public abstract class ReaderHandler implements Handler<ReaderHandler> {

    protected ReaderHandler nextHandler;

    public void setNextHandler(ReaderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public ReaderHandler getNextHandler() {
        return nextHandler;
    }

    public abstract byte[] handleRequets();

    public abstract void skip(long length);

    public abstract Cabecalho getCabecalho() throws FileNotFoundException, IOException, ClassNotFoundException;
}
