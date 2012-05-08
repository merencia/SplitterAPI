package org.jsplitter.facade.proxy;

import org.jsplitter.facade.JSplitterFacade;
import java.io.File;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.strategy.Strategy;
import org.jsplitter.validation.StrategyValidacao;
import org.jsplitter.strategy.StrategyFactory;
import org.jsplitter.validation.Validation;

public class JSplitterFacadeProxy extends JSplitterFacade {

    @Override
    public Validation split(File file, int partes, File destino) {
        Validation validacao = validacaoSplit(new Cabecalho(file, partes));
        if (validacao.isOk()) {
            return super.split(file, partes, destino);
        } else {
            return validacao;
        }
    }

    @Override
    public Validation split(File file, long tamanhoParte, File destino) {
        Validation validacao = validacaoSplit(new Cabecalho(file, tamanhoParte));
        if (validacao.isOk()) {
            return super.split(file, tamanhoParte, destino);
        } else {
            return validacao;
        }
    }

    private Validation validacaoSplit(Cabecalho cabecalho) {
        StrategyValidacao strategy = StrategyFactory.createStrategy(Strategy.SPLIT_VALIDACAO_STRATEGY);
        Validation validacao = strategy.doStrategy(cabecalho);
        return validacao;
    }
}
