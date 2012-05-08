package org.jsplitter.exception;

public class NotDirectoryException extends Exception {
    
    public NotDirectoryException(String file){
        super(file);
    }
    
    public NotDirectoryException(){
        super("The specified directory path for the writing the file(s) is not a valid directory path.");
    }
}
