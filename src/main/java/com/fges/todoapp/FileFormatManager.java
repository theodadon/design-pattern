package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileFormatManager {
    void insertTodo(Path filePath, Todo todo) throws IOException;
    List<Todo> listTodos(Path filePath) throws IOException;
}
