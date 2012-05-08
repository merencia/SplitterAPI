package org.jsplitter.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsplitter.core.interfaces.JFileWriter;
import org.jsplitter.exception.NotDirectoryException;

/*
 * Padrões: Adapter
 */
/**
 *
 * @author Lucas
 */
public class JFileWriterImpl implements JFileWriter {

    private File file;
    private FileOutputStream outPut;

    public JFileWriterImpl(File file, boolean append) throws FileNotFoundException, NotDirectoryException, IOException {
        this.file = file;
        if (file.exists() && !append) {
            file.delete();            
            file.createNewFile();
        }
        if (file.getParentFile() == null) {
            throw new NotDirectoryException(file.getAbsolutePath());
        } else if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        outPut = new FileOutputStream(file, append);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        outPut.write(bytes);
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void close() throws IOException {
        outPut.close();
    }

    @Override
    public void flush() throws IOException {
        outPut.flush();
    }
}
