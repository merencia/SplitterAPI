package org.jsplitter.core;

import java.io.File;
import java.io.Serializable;

public class Cabecalho implements Serializable {

    private int partes;
    private long tamanho;
    private long tamanhoParte;
    private String diretorio;
    private boolean byPart;
    private String fileName;

    public Cabecalho(File file, int partes) {
        init(file.getName(), partes, file.length(), file.getParent());
    }

    public Cabecalho(File file, long tamanho) {
        init(file.getName(), tamanho, file.length(), file.getParent());
    }

    private void init(String fileName, int partes, long tamanho, String diretorio) {
        this.fileName = fileName;
        this.partes = partes;
        this.tamanho = tamanho;
        this.tamanhoParte = (long) Math.ceil(Double.valueOf(tamanho) / Double.valueOf(partes));
        this.diretorio = diretorio;
        byPart = true;
    }

    private void init(String fileName, long tamanhoParte, long tamanho, String diretorio) {
        this.fileName = fileName;
        this.partes = (int) Math.ceil(Double.valueOf(tamanho) / Double.valueOf(tamanhoParte));
        this.tamanho = tamanho;
        this.tamanhoParte = tamanhoParte;
        this.diretorio = diretorio;
    }

    public String getFileName() {
        return fileName;
    }

    public int getPartes() {
        return partes;
    }

    public long getTamanho() {
        return tamanho;
    }

    public boolean isByPart() {
        return byPart;
    }

    public long getTamanhoParte() {
        return tamanhoParte;
    }

    public String getDiretorio() {
        return diretorio;
    }
}
