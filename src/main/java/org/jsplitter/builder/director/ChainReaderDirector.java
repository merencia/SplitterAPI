package org.jsplitter.builder.director;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsplitter.builder.factory.ChainBuilderFactory;
import org.jsplitter.builder.impl.ChainBuilder;
import org.jsplitter.core.collection.JSIterator;
import org.jsplitter.core.collection.JSList;
import org.jsplitter.exception.ChainFilesIncompleteException;
import org.jsplitter.exception.NoJSplitterFile;
import org.jsplitter.handlers.abstracts.ReaderHandler;
import org.jsplitter.handlers.impl.FileReaderHandler;
import org.jsplitter.handlers.impl.FileReaderHandlerProxy;
import org.jsplitter.utils.FilesFinder;

/*
 * Padrão: Builder
 */
public class ChainReaderDirector {

    public ReaderHandler build(String filePath) throws ChainFilesIncompleteException, NoJSplitterFile, ClassNotFoundException, FileNotFoundException, IOException {
        return build(new File(filePath));
    }

    public ReaderHandler build(File file) throws ChainFilesIncompleteException, NoJSplitterFile, FileNotFoundException, ClassNotFoundException, IOException {
        FileReaderHandlerProxy firstHandler = new FileReaderHandlerProxy(file);
        //---------------------------------------------------                
        JSList<File> files = FilesFinder.getChainFiles(firstHandler.getCabecalho(), file);
        ChainBuilder<ReaderHandler> builder = ChainBuilderFactory.createBuilder(ChainBuilderFactory.READER);
        JSIterator<File> iterator = files.createIterator();

        builder.addNext(firstHandler);
        while (!iterator.isDone()) {
            builder.addNext(new FileReaderHandler(iterator.getCurrent()));
            iterator.next();
        }

        return builder.build();
    }
}
