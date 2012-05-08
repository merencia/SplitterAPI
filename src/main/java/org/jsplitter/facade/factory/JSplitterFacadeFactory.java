package org.jsplitter.facade.factory;

import org.jsplitter.facade.JSplitterFacade;
import org.jsplitter.facade.proxy.JSplitterFacadeProxy;

public class JSplitterFacadeFactory {
    
    public static JSplitterFacade createInstance(){
        return new JSplitterFacadeProxy();
    }
    
}
