package org.jsplitter.builder.factory;

import org.jsplitter.builder.impl.ChainBuilder;
import org.jsplitter.handlers.abstracts.ReaderHandler;
import org.jsplitter.handlers.abstracts.WriterHandler;
/*
 * Padrões: Builder, Factory
 */

public class ChainBuilderFactory {

    public static final String WRITER = "Writer";
    public static final String READER = "Reader";

    public static ChainBuilder createBuilder(String type) {
        if (READER.equals(type)) {
            return new ChainBuilder<ReaderHandler>();
        } else if (WRITER.equals(type)) {
            return new ChainBuilder<WriterHandler>();
        }
        return null;
    }
}
