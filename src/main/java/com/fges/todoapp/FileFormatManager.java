package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/*Définit le contrat pour les gestionnaires de formats de fichiers.
Cette interface spécifie les méthodes pour insérer un nouveau todo et lister les todos existants dans un fichier.*/
public interface FileFormatManager {
    void insertTodo(Path filePath, Todo todo) throws IOException;
    List<Todo> listTodos(Path filePath) throws IOException;
}
