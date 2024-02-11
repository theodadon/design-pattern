package com.fges.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonManager implements FileFormatManager {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void insertTodo(Path filePath, Todo todo) throws IOException {
        List<Todo> todos;
        if (Files.exists(filePath) && Files.size(filePath) > 0) {
            todos = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Todo>>() {});
        } else {
            todos = new ArrayList<>();
        }
        todos.add(todo);
        objectMapper.writeValue(filePath.toFile(), todos);
    }

    @Override
    public List<Todo> listTodos(Path filePath) throws IOException {
        if (!Files.exists(filePath) || Files.size(filePath) == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(filePath.toFile(), new TypeReference<List<Todo>>() {});
    }
}
