package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * FileManager fournit des méthodes utilitaires statiques pour lire et écrire le contenu des fichiers.
 * Cette classe simplifie les opérations sur fichiers communes.
 */
public class FileManager {

    public static String readFileContent(Path filePath) throws IOException {
        return Files.exists(filePath) ? new String(Files.readAllBytes(filePath)) : "";
    }

    public static void writeFileContent(Path filePath, String content) throws IOException {
        Files.write(filePath, content.getBytes());
    }
}
