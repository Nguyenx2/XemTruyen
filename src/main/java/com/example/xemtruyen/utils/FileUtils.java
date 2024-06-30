package com.example.xemtruyen.utils;

import com.example.xemtruyen.exceptions.ConflictException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static final String UPLOADS_FOLDER = "uploads";

    public static void deleteFile(String fileName) {
        Path uploadDir = Paths.get(UPLOADS_FOLDER);
        Path filePath = uploadDir.resolve(fileName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new ConflictException("Cannot delete file !");
        }
    }

    public static void deleteFolder(String folderPath) {
        File directory = new File(folderPath);
        if (directory.exists()) {
            deleteRec(directory);
        }
    }

    private static void deleteRec(File fileOrDirectory) {
        File[] contents = fileOrDirectory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteRec(file);
            }
            fileOrDirectory.delete();
        }
    }


}
