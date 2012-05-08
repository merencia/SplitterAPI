/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsplitter.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsplitter.core.Cabecalho;
import org.jsplitter.core.collection.JSList;
import org.jsplitter.core.collection.JSListImpl;
import org.jsplitter.exception.ChainFilesIncompleteException;
import org.jsplitter.exception.NoJSplitterFile;

/**
 *
 * @author Lucas
 */
public class FilesFinder {

    public static boolean fileExists(String path) {
        File f = new File(path);
        return f.exists();
    }

    public static boolean chainFilesExists(Cabecalho cabecalho, File firstFile) {
        try {
            JSList<File> files = getChainFiles(cabecalho, firstFile);
            return true;
        } catch (ChainFilesIncompleteException ex) {
            Logger.getLogger(FilesFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoJSplitterFile ex) {
            Logger.getLogger(FilesFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static JSList<File> getChainFiles(Cabecalho cabecalho, File firstFile) throws ChainFilesIncompleteException, NoJSplitterFile {
        JSList<File> files = null;
        if (firstFile == null || !firstFile.getName().endsWith(".js001")) {
            throw new NoJSplitterFile();
        } else {
            files = new JSListImpl<File>();
            for (int i = 1; i < cabecalho.getPartes(); i++) {
                String num = "";
                if (i < 9) {
                    num = ".js00" + (i + 1);
                } else if (i >= 100) {
                    num = ".js" + (i + 1);
                } else {
                    num = ".js0" + (i + 1);
                }
                File f = new File(firstFile.getParent()+ "/" + cabecalho.getFileName() + num);
                if (!f.exists()) {
                    throw new ChainFilesIncompleteException(f.getName());
                } else {
                    files.add(f);
                }
            }
        }
        return files;
    }
}
