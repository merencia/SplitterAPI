package org.jsplitter.observer;

import java.util.ArrayList;
import org.jsplitter.visitor.Visitor;

/**
 *Template Method com Observer
 * @author Lucas
 */
public abstract class Subject<T> {

    private ArrayList<Observer<T>> observers = new ArrayList<Observer<T>>();

    public boolean attach(Observer<T> observer) {
        return observers.add(observer);
    }

    public boolean dettach(Observer<T> observer) {
        return observers.remove(observer);
    }

    public void updateAll() {
        for (Observer<T> observer : observers) {
            observer.receiveUpdate(getNewValue());
        }
    }
    
    public void updateAll(Visitor<Observer> visitor){
        for (Observer observer : observers) {
            visitor.visit(observer);
        }
    }

    public abstract T getNewValue();
}
