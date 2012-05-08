package org.jsplitter.handlers.impl;

import java.io.File;
import java.io.FileNotFoundException;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.core.JFileReaderImpl;
import org.jsplitter.handlers.abstracts.ReaderHandler;

/*
 * Padrões: Chain, Proxy
 */
/**
 *
 * @author Lucas
 */
public class FileReaderHandler extends ReaderHandler {

    private JFileReaderImpl fr = null;

    public FileReaderHandler(File file) throws FileNotFoundException {
        fr = new JFileReaderImpl(file);
    }

    public byte[] read() {
        return fr.read();
    }

    @Override
    public byte[] handleRequets() {
        if (fr.avaiable() > 0) {
            return fr.read();
        } else if (nextHandler != null) {
            return nextHandler.handleRequets();
        } else {
            return null;
        }
    }

    @Override
    public void skip(long length) {
        fr.skip(length);
    }

    @Override
    public Cabecalho getCabecalho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
