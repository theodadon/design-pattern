package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private static final Path logFilePath = Paths.get("application.log");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(dtf);
        String logMessage = timestamp + " - " + message + "\n";

        try {
            Path parentDir = logFilePath.getParent();
            // Vérifiez si le parent est non-nul avant d'essayer de créer des répertoires
            if (parentDir != null && Files.notExists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            Files.write(logFilePath, logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
}
