package org.jsplitter.handlers.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.exception.NotDirectoryException;
import org.jsplitter.handlers.abstracts.WriterHandler;

/*
 * Padrão: Chain, Proxy
 */
/**
 * 
 * @author Lucas
 */
public final class FileWriterHandlerProxy extends WriterHandler {

    private Cabecalho cabecalho;
    private File file;
    private boolean isWrote = false;

    public FileWriterHandlerProxy(File file, Cabecalho cabecalho, long maxLenght) throws FileNotFoundException {
        this.cabecalho = cabecalho;
        this.file = file;
        super.setNextHandler(new FileWriteHandler(file, maxLenght, true));
    }

    @Override
    public void handleRequets(byte[] bytes) throws FileNotFoundException, IOException, SecurityException, NotDirectoryException {
        if (!isWrote) {
            writeCabecalho();
            isWrote = true;
        }
        if (getNextHandler() != null) {
            getNextHandler().handleRequets(bytes);
        }
    }

    @Override
    public void setNextHandler(WriterHandler nextHandler) {
        getNextHandler().setNextHandler(nextHandler);
    }

    public void writeCabecalho() throws FileNotFoundException, IOException {
        FileOutputStream fos = null;
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cabecalho);
        oos.flush();
        oos.close();
    }
}
