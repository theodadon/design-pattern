package com.fges.todoapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * JsonManager implémente l'interface FileFormatManager pour gérer les opérations de lecture et d'écriture des TODOs dans des fichiers JSON.
 * Fournit des méthodes pour insérer, lister et écrire des tâches TODO.
 */
public class JsonManager implements FileFormatManager {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void insertTodo(Path filePath, Todo todo) throws IOException {
        List<Todo> todos;
        if (Files.exists(filePath) && Files.size(filePath) > 0) {
            todos = objectMapper.readValue(filePath.toFile(), new TypeReference<>() {
            });
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
        return objectMapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
    }
    @Override
    public void writeTodos(Path filePath, List<Todo> todos) throws IOException {
        objectMapper.writeValue(filePath.toFile(), todos);
    }
}
