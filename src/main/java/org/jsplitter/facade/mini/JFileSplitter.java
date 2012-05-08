package org.jsplitter.facade.mini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.builder.director.ChainWriterDirector;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.core.JFileReaderImpl;
import org.jsplitter.exception.NotDirectoryException;
import org.jsplitter.handlers.abstracts.WriterHandler;
import org.jsplitter.observer.Subject;

/**
 *
 * @author Lucas
 */
public class JFileSplitter extends Subject<Long> {

    private Cabecalho cabecalho;
    private WriterHandler writer = null;
    private JFileReaderImpl reader = null;
    private File destino;
    private Long progress;

    public JFileSplitter(File file, int partes, File destino) throws FileNotFoundException {
        this.cabecalho = new Cabecalho(file, partes);
        reader = new JFileReaderImpl(file);
        this.destino = destino;
        initilize();
    }

    public JFileSplitter(File file, long tamanho, File destino) throws FileNotFoundException {
        this.cabecalho = new Cabecalho(file, tamanho);
        reader = new JFileReaderImpl(file);
        this.destino = destino;
        initilize();
    }

    private void initilize() throws SecurityException, FileNotFoundException {
        ChainWriterDirector director = new ChainWriterDirector();
        writer = director.build(cabecalho, destino);
    }

    public void split() throws IOException, SecurityException, NotDirectoryException {
        byte[] bytes;
        while ((bytes = reader.read()).length > 0) {
            writer.handleRequets(bytes);
            progress = Long.valueOf(bytes.length);
            updateAll();
        }
    }

    @Override
    public Long getNewValue() {
        return progress;
    }
}
