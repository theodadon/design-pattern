package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static String readFileContent(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            return Files.readString(filePath);
        }
        return "";
    }

    public static void writeFileContent(Path filePath, String content) throws IOException {
        Files.writeString(filePath, content);
    }
}
