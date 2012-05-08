package org.jsplitter.exception;

/**
 *
 * @author Lucas
 */
public class NoJSplitterFile extends Exception{

    public NoJSplitterFile() {
        super("The specified file can not be recognized by the application");
    }
    
}
