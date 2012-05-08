package org.jsplitter.strategy;


import org.jsplitter.validation.Validation;

public interface Strategy<P> {
    
    public static final String SPLIT_VALIDACAO_STRATEGY = "splitValidacao";

    public Validation doStrategy(P param);
}
