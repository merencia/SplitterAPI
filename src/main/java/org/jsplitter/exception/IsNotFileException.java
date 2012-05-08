package org.jsplitter.exception;

public class IsNotFileException extends Exception {

    public IsNotFileException() {
        super("The specified path is not a file");
    }
}
