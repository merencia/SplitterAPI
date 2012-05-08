package org.jsplitter.facade.mini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.builder.director.ChainReaderDirector;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.core.interfaces.JFileWriter;
import org.jsplitter.core.proxy.JFileWriterProxy;
import org.jsplitter.exception.ChainFilesIncompleteException;
import org.jsplitter.exception.NoJSplitterFile;
import org.jsplitter.exception.NotDirectoryException;
import org.jsplitter.handlers.abstracts.ReaderHandler;
import org.jsplitter.observer.Subject;

/**
 *
 * @author Lucas
 */
public class JFileJoiner extends Subject<Long> {

    private ReaderHandler reader = null;
    private JFileWriter fw = null;
    private Long progress = 0L;

    public JFileJoiner(File firstFile, String path) throws FileNotFoundException, NoJSplitterFile, ChainFilesIncompleteException, ClassNotFoundException, IOException {
        ChainReaderDirector director = new ChainReaderDirector();
        reader = director.build(firstFile);
        Cabecalho c = reader.getCabecalho();
        File file = new File(path + "/" + c.getFileName());
        fw = new JFileWriterProxy(file);
    }

    public void join() throws IOException, NotDirectoryException {
        byte[] bytes;
        while ((bytes = reader.handleRequets()) != null) {
            fw.write(bytes);
            progress = Long.valueOf(bytes.length);
            updateAll();
        }
        fw.flush();
        fw.close();
    }

    public Cabecalho getCabecalho() throws FileNotFoundException, IOException, ClassNotFoundException {
        return reader.getCabecalho();
    }

    @Override
    public Long getNewValue() {
        return progress;
    }
}
