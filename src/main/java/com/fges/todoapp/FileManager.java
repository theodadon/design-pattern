package com.fges.todoapp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static Path getFilePath(String fileName) {
        return Paths.get(fileName);
    }
}
