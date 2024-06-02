package com.example.xemtruyen.utils;

import java.io.File;

public class FileUtils {
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }
    public static void deleteFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file.getAbsolutePath());
                    } else {
                        if (!file.delete()) {
                            return;
                        }
                    }
                }
            }
            folder.delete();
        }
    }
}
