package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Interface FileFormatManager définie le contrat pour les gestionnaires de formats de
 * fichiers spécifiques dans l'application.
 * Cette interface est utilisée pour abstraire les opérations de gestion de todos
 * (comme l'insertion et la récupération)
 * de leurs représentations spécifiques de format de fichier. Les classes qui implémentent
 * cette interface sont responsables
 * de fournir la logique concrète pour manipuler les todos dans différents formats de
 * fichiers (par exemple, JSON, CSV, XML, etc.).

 * En utilisant cette interface, l'application peut facilement étendre sa
 * prise en charge à d'autres formats de fichiers en
 * ajoutant de nouvelles implémentations sans modifier la logique centrale de
 * manipulation des todos.
 */
public interface FileFormatManager {
    void insertTodo(Path filePath, Todo todo) throws IOException;
    List<Todo> listTodos(Path filePath) throws IOException;
}
