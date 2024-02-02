package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;

public interface FileFormatManager {
    void insertTodo(Path filePath, String todo) throws IOException;
    void listTodos(String fileContent) throws IOException;
}
