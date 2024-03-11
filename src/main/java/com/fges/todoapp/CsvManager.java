package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvManager implements FileFormatManager {

    @Override
    public void insertTodo(Path filePath, Todo todo) throws IOException {
        List<String> lines = Files.exists(filePath) ? Files.readAllLines(filePath) : new ArrayList<>();
        String line = String.format("%s,%s,%s,%s", todo.getDescription(), todo.getAuthor(), todo.getColor(), todo.isDone() ? "DONE" : "PENDING");
        lines.add(line);
        Files.write(filePath, lines);
    }

    @Override
    public List<Todo> listTodos(Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        List<String> lines = Files.readAllLines(filePath);
        List<Todo> todos = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",", -1);
            String description = parts[0];
            String author = parts.length > 1 ? parts[1] : "Default Author";
            String color = parts.length > 2 ? parts[2] : "Default Color";
            boolean done = parts.length > 3 && "DONE".equals(parts[3]);
            todos.add(new Todo(description, author, color, done));
        }
        return todos;
    }
}
