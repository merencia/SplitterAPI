package org.jsplitter.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsplitter.facade.mini.JFileJoiner;
import org.jsplitter.facade.mini.JFileSplitter;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.validation.Validation;
import org.jsplitter.exception.ChainFilesIncompleteException;
import org.jsplitter.exception.NoJSplitterFile;
import org.jsplitter.exception.NotDirectoryException;
import org.jsplitter.handlers.impl.FileReaderHandlerProxy;
import org.jsplitter.observer.Observer;

public class JSplitterFacade {

    private ArrayList<Observer<Long>> observers = new ArrayList<Observer<Long>>();

    public Validation split(File file, int partes, File destino) {
        try {
            JFileSplitter jFileSplitter = new JFileSplitter(file, partes, destino);
            return split(jFileSplitter);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Validation(false);
    }

    public Validation split(File file, long tamanhoParte, File destino) {
        try {
            JFileSplitter jFileSplitter = new JFileSplitter(file, tamanhoParte, destino);
            return split(jFileSplitter);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Validation(false);
    }

    private Validation split(JFileSplitter splitter) {
        String message = "Erro inesperado.";
        try {
            for (Observer<Long> observer : observers) {
                splitter.attach(observer);
            }
            splitter.split();

            return new Validation(true, "Arquivo dividido com sucesso!");
        } catch (NotDirectoryException ex) {
            message = "O diretório do arquivo " + ex.getMessage() + " selecionado não é um diretório válido ou não pode ser criado!";
        } catch (SecurityException ex) {
            message = "A aplicação não possui permissões suficientes para criar as partes ou o diretório de destino!";
        } catch (FileNotFoundException ex) {
            message = "O arquivo de divisão não foi encontrado ou a aplicação não possui permissões suficientes para criar as partes ou o diretório de destino!";
        } catch (IOException ex) {
            message = "Ocorreu um erro inesperado ao tentar dividir os arquivos!";
        }
        return new Validation(false, message);
    }

    public Validation join(File file, String path) {
        try {
            JFileJoiner fileJoiner = new JFileJoiner(file, path);
            for (Observer<Long> observer : observers) {
                fileJoiner.attach(observer);
            }
            fileJoiner.join();
            return new Validation(true, "Arquivo juntado com sucesso");
        } catch (NotDirectoryException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoJSplitterFile ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ChainFilesIncompleteException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Validation(false);
    }

    public Cabecalho getCabecalho(File file) {
        try {
            FileReaderHandlerProxy fr = new FileReaderHandlerProxy(file);
            Cabecalho c = fr.getCabecalho();
            return c;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoJSplitterFile ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSplitterFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean addObserver(Observer<Long> observer) {
        return observers.add(observer);
    }

    public boolean removeObserver(Observer<Long> observer) {
        return observers.remove(observer);
    }
}
