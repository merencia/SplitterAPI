/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsplitter.exception;

/**
 *
 * @author Lucas
 */
public class ChainFilesIncompleteException extends Exception{

    public ChainFilesIncompleteException() {
        super("Some files were not found");
    }
    
    public ChainFilesIncompleteException(String fileMissing) {
        super("The file "+fileMissing+" were not found");
    }
    
}
