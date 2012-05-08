package org.jsplitter.core.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.exception.NotDirectoryException;

public interface JFileWriter {

    public void write(byte[] bytes) throws IOException, NotDirectoryException;

    public File getFile() throws FileNotFoundException, IOException, NotDirectoryException;

    public void close() throws IOException, NotDirectoryException;

    public void flush() throws IOException, NotDirectoryException;
}
