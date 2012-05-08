package org.jsplitter.validation.impl;

import java.io.File;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.validation.StrategyValidacao;
import org.jsplitter.validation.Validation;
import org.jsplitter.utils.FileUtils;

public class SplitValidacaoStrategy implements StrategyValidacao {

    @Override
    public Validation doStrategy(Cabecalho param) {
        boolean valido = true;
        String message = null;
        File file = new File(param.getDiretorio() + "/" + param.getFileName());
        if (file == null || !file.exists() || !file.isFile()) {
            valido = false;
            message = "O arquivo especificado não existe ou não é um arquivo.";
        } else if (param.getPartes() < 2 || param.getPartes() > 999) {
            valido = false;
            message = "A quantidade de partes mínima é de 2 e o máximo é de 999.";
        } else if (param.getTamanhoParte() > param.getTamanho() * 0.9 || param.getTamanhoParte() < 1024 * 1024) {
            valido = false;
            message = "O tamanho mínimo de uma parte deve ser de 1MB e o máximo de 90% do arquivo a ser dividido (" + FileUtils.getInstance().getSizeAsString(param.getTamanho() * 0.9) + ").";
        }
        return new Validation(valido, message);
    }
}
