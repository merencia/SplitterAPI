package org.jsplitter.core.proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.core.JFileWriterImpl;
import org.jsplitter.core.interfaces.JFileWriter;
import org.jsplitter.exception.NotDirectoryException;

/**
 * Proxy Virtual
 * @author giovaniguizzo
 */
public class JFileWriterProxy implements JFileWriter {

    private File file = null;
    private boolean append = false;
    private JFileWriterImpl fileWriter = null;

    public JFileWriterProxy(File file) throws FileNotFoundException {
        this.file = file;
    }

    public JFileWriterProxy(File file, boolean append) throws FileNotFoundException {
        this.file = file;
        this.append = append;
    }

    @Override
    public void write(byte[] bytes) throws FileNotFoundException, IOException, NotDirectoryException {
        if (fileWriter == null) {
            fileWriter = new JFileWriterImpl(file, append);
        }
        fileWriter.write(bytes);
    }

    @Override
    public File getFile() throws FileNotFoundException, IOException, NotDirectoryException {
        if (fileWriter == null) {
            fileWriter = new JFileWriterImpl(file, append);
        }
        return fileWriter.getFile();
    }

    @Override
    public void close() throws FileNotFoundException, IOException, NotDirectoryException {
        if (fileWriter == null) {
            fileWriter = new JFileWriterImpl(file, append);
        }
        fileWriter.close();
    }

    @Override
    public void flush() throws FileNotFoundException, IOException, NotDirectoryException {
        if (fileWriter == null) {
            fileWriter = new JFileWriterImpl(file, append);
        }
        fileWriter.flush();
    }
}
