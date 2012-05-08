/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsplitter.observer;

/**
 *
 * @author Lucas
 */
public interface Observer<T> {

    public void receiveUpdate(T value);
    public void receiveErrorMessage(String message);
    public void receiveDoneMessage(String message);
}
