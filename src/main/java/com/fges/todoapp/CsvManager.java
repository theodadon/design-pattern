package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvManager implements FileFormatManager {

    @Override
    public void insertTodo(Path filePath, Todo todo) throws IOException {
        // Assurez-vous que le fichier existe avant d'essayer d'écrire dedans
        List<String> lines = Files.exists(filePath) ? Files.readAllLines(filePath) : new ArrayList<>();
        // Encapsule chaque champ entre guillemets et remplace les guillemets internes
        String line = encapsulateCsvField(todo.getDescription()) + "," +
                encapsulateCsvField(todo.getAuthor()) + "," +
                encapsulateCsvField(todo.getColor()) + "," +
                (todo.isDone() ? "DONE" : "PENDING");
        lines.add(line);
        Files.write(filePath, lines);
    }

    @Override
    public List<Todo> listTodos(Path filePath) throws IOException {
        List<Todo> todos = new ArrayList<>();
        if (Files.exists(filePath)) {
            List<String> lines = Files.readAllLines(filePath);
            for (String rawLine : lines) {
                // Split la ligne en se basant sur la virgule en dehors des guillemets
                String[] parts = rawLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String description = unencapsulateCsvField(parts[0]);
                String author = parts.length > 1 ? unencapsulateCsvField(parts[1]) : "Anonymous";
                String color = parts.length > 2 ? unencapsulateCsvField(parts[2]) : "None";
                boolean done = parts.length > 3 && parts[3].equals("DONE");
                todos.add(new Todo(description, author, color, done));
            }
        }
        return todos;
    }

    private String encapsulateCsvField(String field) {
        // Encapsule le champ dans des guillemets et échappe les guillemets internes
        return "\"" + field.replace("\"", "\"\"") + "\"";
    }

    private String unencapsulateCsvField(String field) {
        // Enlève les guillemets encapsulants et dé-échappe les guillemets internes
        return field.replaceFirst("^\"", "").replaceFirst("\"$", "").replace("\"\"", "\"");
    }

    @Override
    public void writeTodos(Path filePath, List<Todo> todos) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Todo todo : todos) {
            // Encapsule et formate chaque champ
            String line = encapsulateCsvField(todo.getDescription()) + "," +
                    encapsulateCsvField(todo.getAuthor()) + "," +
                    encapsulateCsvField(todo.getColor()) + "," +
                    (todo.isDone() ? "DONE" : "PENDING");
            lines.add(line);
        }
        Files.write(filePath, lines);
    }
}
