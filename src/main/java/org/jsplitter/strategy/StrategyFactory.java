package org.jsplitter.strategy;

import org.jsplitter.strategy.Strategy;
import org.jsplitter.validation.StrategyValidacao;
import org.jsplitter.validation.impl.SplitValidacaoStrategy;

public class StrategyFactory {
    
    public static StrategyValidacao createStrategy(String type){
        if(Strategy.SPLIT_VALIDACAO_STRATEGY.equals(type)){
            return new SplitValidacaoStrategy();
        }
        return null;
    }
    
}
