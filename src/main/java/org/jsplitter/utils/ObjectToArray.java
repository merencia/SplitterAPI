package org.jsplitter.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class ObjectToArray {
    public static byte[] toArray(Object c){
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(c);
            oos.flush();
            oos.close();
            bos.close();
            byte[] data = bos.toByteArray();
            return data;
        } catch (IOException ex) {
            Logger.getLogger(ObjectToArray.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ObjectToArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
