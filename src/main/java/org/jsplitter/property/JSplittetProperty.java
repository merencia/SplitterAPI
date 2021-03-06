package org.jsplitter.property;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class JSplittetProperty {

    private static final JSplittetProperty INSTANCE = new JSplittetProperty();
    private Properties proprerties = new Properties();
    private static final String URL_PROPERTIES = "/resources/propriedades.properties";
            
    private JSplittetProperty() {
        loadPropertis();
    }

    public static JSplittetProperty getInstance() {
        return INSTANCE;
    }
    
    private void loadPropertis(){
        try {
            proprerties.load(JSplittetProperty.class.getResourceAsStream(URL_PROPERTIES));
        } catch (IOException ex) {
            Logger.getLogger(JSplittetProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getProperty(String key){
        return proprerties.getProperty(key);
    }
}
