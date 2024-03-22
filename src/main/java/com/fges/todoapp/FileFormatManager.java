package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
/**
 * L'interface FileFormatManager définit les méthodes pour la gestion des fichiers contenant des TODOs,
 * permettant l'insertion, la lecture, et l'écriture de TODOs dans différents formats de fichiers.
 */
public interface FileFormatManager {

    void insertTodo(Path filePath, Todo todo) throws IOException;

    List<Todo> listTodos(Path filePath) throws IOException;


    default void writeTodos(Path filePath, List<Todo> todos) throws IOException {
        throw new UnsupportedOperationException("This method is not implemented.");
    }
}
