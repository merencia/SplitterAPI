package org.jsplitter.handlers.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.core.proxy.JFileWriterProxy;
import org.jsplitter.exception.NotDirectoryException;
import org.jsplitter.handlers.abstracts.WriterHandler;

/*
 * Padrões: Chain, Proxy
 */
/**
 *
 * @author Lucas
 */
public class FileWriteHandler extends WriterHandler {

    private long maxLenth = 0;
    private long length = 0;
    private boolean closed = true;
    private JFileWriterProxy fw = null;
    private File file = null;

    public FileWriteHandler(File file, long maxLength) throws FileNotFoundException {
        fw = new JFileWriterProxy(file);
        closed = false;
        this.maxLenth = maxLength;
        this.file = file;
        if (file.exists()) {
            file.delete();
        }
    }

    public FileWriteHandler(File file, long maxLength, boolean append) throws FileNotFoundException {
        fw = new JFileWriterProxy(file, append);
        closed = false;
        this.maxLenth = maxLength;
        this.file = file;
        if (file.exists() && !append) {
            file.delete();
        }
    }

    @Override
    public void handleRequets(byte[] bytes) throws SecurityException, IOException, FileNotFoundException, NotDirectoryException {
        length = file.length();
        if (closed) {
            getNextHandler().handleRequets(bytes);
        } else {
            if (maxLenth >= length + bytes.length) {
                write(bytes);
            } else {
                int size = (int) (maxLenth - length);
                if (size > 0) {
                    byte[] buffer = new byte[size];
                    System.arraycopy(bytes, 0, buffer, 0, size);
                    write(buffer);
                }
                byte[] toNext = new byte[bytes.length - size];
                System.arraycopy(bytes, size, toNext, 0, toNext.length);
                if (getNextHandler() != null) {
                    getNextHandler().handleRequets(toNext);
                } else {
                    System.out.println("Não foram gravados " + toNext.length + " Bytes");
                }
                closed = true;
                fw.flush();
                fw.close();
            }
        }
    }

    private void write(byte[] bytes) throws SecurityException, IOException, FileNotFoundException, NotDirectoryException{
        fw.write(bytes);
    }
}
