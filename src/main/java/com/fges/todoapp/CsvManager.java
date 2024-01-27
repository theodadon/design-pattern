package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class CsvManager implements TodoListManager {
    private final Path filePath;

    public CsvManager(String fileName) {
        this.filePath = FileManager.getFilePath(fileName);
    }

    @Override
    public void insert(String todo) throws IOException {
        StringBuilder content = new StringBuilder();
        if (Files.exists(filePath)) {
            content.append(Files.readString(filePath));
        }
        if (!content.isEmpty()) {
            content.append("\n");
        }
        content.append(todo);
        Files.writeString(filePath, content.toString());
        System.out.println("Todo ajouté avec succès");
    }

    @Override
    public void list() throws IOException {
        if (!Files.exists(filePath)) {
            System.out.println("Aucun todo trouvé.");
            return;
        }

        Files.lines(filePath).forEach(todo -> System.out.println("- " + todo));
    }
}
