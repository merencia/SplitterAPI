package org.jsplitter.handlers.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.exception.NoJSplitterFile;
import org.jsplitter.handlers.abstracts.ReaderHandler;
import org.jsplitter.utils.ObjectToArray;

/*
 * Padrões: Chain, Proxy
 */
/**
 *
 * @author Lucas
 */
public final class FileReaderHandlerProxy extends ReaderHandler {

    private Cabecalho cabecalho;
    private File file;

    public FileReaderHandlerProxy(File firstFile) throws FileNotFoundException, NoJSplitterFile, ClassNotFoundException, IOException {
        this.file = firstFile;
        if (!file.getName().endsWith(".js001")) {
            throw new NoJSplitterFile();
        }
        super.nextHandler = new FileReaderHandler(file);
        cabecalho = readCabecalho();
    }

    @Override
    public byte[] handleRequets() {
        if (nextHandler != null) {
            return nextHandler.handleRequets();
        }
        return null;
    }

    private Cabecalho readCabecalho() throws FileNotFoundException, IOException, ClassNotFoundException {
        Cabecalho c = null;
        FileInputStream fis = null;
        {
            ObjectInputStream ois = null;
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            c = (Cabecalho) ois.readObject();
            ois.close();
            nextHandler.skip(ObjectToArray.toArray(c).length);
        }
        return c;
    }

    @Override
    public void skip(long length) {
        nextHandler.skip(length);
    }

    @Override
    public Cabecalho getCabecalho() {
        return cabecalho;
    }

    @Override
    public void setNextHandler(ReaderHandler nextHandler) {
        super.nextHandler.setNextHandler(nextHandler);
    }
}
