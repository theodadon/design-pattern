package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * CsvManager implémente l'interface FileFormatManager pour gérer les opérations de lecture et d'écriture des TODOs dans des fichiers CSV.
 */
public class CsvManager implements FileFormatManager {

    @Override
    public void insertTodo(Path filePath, Todo todo) throws IOException {
        List<String> lines = Files.exists(filePath) ? Files.readAllLines(filePath) : new ArrayList<>();
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
        return "\"" + field.replace("\"", "\"\"") + "\"";
    }

    private String unencapsulateCsvField(String field) {
        return field.replaceFirst("^\"", "").replaceFirst("\"$", "").replace("\"\"", "\"");
    }

    @Override
    public void writeTodos(Path filePath, List<Todo> todos) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Todo todo : todos) {
            String line = encapsulateCsvField(todo.getDescription()) + "," +
                    encapsulateCsvField(todo.getAuthor()) + "," +
                    encapsulateCsvField(todo.getColor()) + "," +
                    (todo.isDone() ? "DONE" : "PENDING");
            lines.add(line);
        }
        Files.write(filePath, lines);
    }
}
