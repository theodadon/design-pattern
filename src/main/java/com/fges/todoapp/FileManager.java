package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static String readFileContent(Path filePath) throws IOException {
        return Files.exists(filePath) ? new String(Files.readAllBytes(filePath)) : "";
    }

    public static void writeFileContent(Path filePath, String content) throws IOException {
        Files.write(filePath, content.getBytes());
    }
}
