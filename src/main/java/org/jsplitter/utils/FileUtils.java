package org.jsplitter.utils;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtils {

    private static final FileUtils INSTANCE = new FileUtils();
    private File lastDir;

    private FileUtils() {
    }

    public static FileUtils getInstance() {
        return INSTANCE;
    }

    public String getSizeAsString(long size) {
        return getSizeAsString(new Double(size));
    }

    public File getLastDir() {
        return lastDir;
    }

    public void setLastDir(File lastDir) {
        this.lastDir = lastDir;
    }

    public String getSizeAsString(File file) {
        String size = null;
        if (file != null) {
            size = getSizeAsString(file.length());
        }
        return size;
    }

    public String getSizeAsString(double percentAsDouble) {
        String sizeString = null;
        String type = "";
        if (percentAsDouble < 1024) {
            type = "B";
        } else if (percentAsDouble < 1024 * 1024) {
            percentAsDouble = percentAsDouble / 1024;
            type = "KB";
        } else if (percentAsDouble < 1024 * 1024 * 1024) {
            percentAsDouble = percentAsDouble / 1024 / 1024;
            type = "MB";
        } else if (percentAsDouble < 1024L * 1024L * 1024L * 1024L) {
            percentAsDouble = percentAsDouble / 1024 / 1024 / 1024;
            type = "GB";
        } else {
            percentAsDouble = percentAsDouble / 1024 / 1024 / 1024 / 1024;
            type = "TB";
        }
        sizeString = new DecimalFormat("#0.00").format(percentAsDouble) + " " + type;
        return sizeString;
    }

    public void incrementFileName(File file) {
        file.renameTo(renameFile(file, 1));
    }

    private File renameFile(File realFile, int count) {
        String newPath = realFile.getPath();
        String oldPath = realFile.getPath();
        newPath = newPath.substring(0, newPath.lastIndexOf("."))
                + " (" + count + ")"
                + newPath.substring(newPath.lastIndexOf("."), newPath.length());
        realFile = new File(newPath);
        if (realFile.exists()) {
            count++;
            return renameFile(new File(oldPath), count);
        } else {
            return realFile;
        }
    }
}
