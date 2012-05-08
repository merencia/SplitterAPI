package org.jsplitter.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsplitter.property.JSplittetProperty;

/*
 * Padrões: Adapter
 */
/**
 *
 * @author Lucas
 */
public class JFileReaderImpl {

    private File file;
    private FileInputStream inPut;

    public JFileReaderImpl(File file) throws FileNotFoundException {
        this.file = file;
        this.inPut = new FileInputStream(file);
    }

    public byte[] read() {
        try {
            int sizeBuffer = Integer.parseInt(JSplittetProperty.getInstance().getProperty("buffer"));
            byte[] bytes = new byte[inPut.available() > sizeBuffer ? sizeBuffer : inPut.available()];
            inPut.read(bytes);
           //System.out.println(bytes.length + " Bytes Lidos do Arquivo" + file.getAbsolutePath());
            return bytes;
        } catch (IOException ex) {
            Logger.getLogger(JFileReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public long avaiable() {
        try {
            return inPut.available();
        } catch (IOException ex) {
            Logger.getLogger(JFileReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public File getFile() {
        return file;
    }

    public long skip(long n) {
        try {
            return inPut.skip(n);
        } catch (IOException ex) {
            Logger.getLogger(JFileReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void close() {
        try {
            inPut.close();
        } catch (IOException ex) {
            Logger.getLogger(JFileReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
