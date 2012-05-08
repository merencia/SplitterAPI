package org.jsplitter.builder.director;

import java.io.File;
import java.io.FileNotFoundException;
import org.jsplitter.builder.factory.ChainBuilderFactory;
import org.jsplitter.builder.impl.ChainBuilder;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.handlers.abstracts.WriterHandler;
import org.jsplitter.handlers.impl.FileWriteHandler;
import org.jsplitter.handlers.impl.FileWriterHandlerProxy;
import org.jsplitter.utils.ObjectToArray;


/*
 * Padrão: Builder
 */
/**
 *
 * @author Lucas
 */
public class ChainWriterDirector {

    public WriterHandler build(Cabecalho cabecalho, File destino) throws SecurityException, FileNotFoundException {
        ChainBuilder<WriterHandler> builder = ChainBuilderFactory.createBuilder(ChainBuilderFactory.WRITER);
        for (int i = 0; i < cabecalho.getPartes(); i++) {
            String num = "";
            if (i < 9) {
                num = ".js00" + (i + 1);
            } else if (i >= 100) {
                num = ".js" + (i + 1);
            } else {
                num = ".js0" + (i + 1);
            }
            File f = new File(destino.getAbsolutePath() + "/" + cabecalho.getFileName() + num);
            long maxLenght = cabecalho.getTamanhoParte();
            if (cabecalho.isByPart()) {
                double parteCabecalho = Double.valueOf(ObjectToArray.toArray(cabecalho).length) / Double.valueOf(cabecalho.getPartes());
                maxLenght += Math.ceil(parteCabecalho);
            }
            if (i == 0) {
                builder.addNext(new FileWriterHandlerProxy(f, cabecalho, maxLenght));
            } else {
                builder.addNext(new FileWriteHandler(f, maxLenght));
            }
        }

        return builder.build();
    }
}
