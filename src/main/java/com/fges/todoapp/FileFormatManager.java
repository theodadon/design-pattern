package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileFormatManager {

    /**
     * Insère une nouvelle tâche TODO dans le fichier spécifié.
     *
     * @param filePath Le chemin vers le fichier où insérer la tâche.
     * @param todo La tâche à insérer.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    void insertTodo(Path filePath, Todo todo) throws IOException;

    /**
     * Liste toutes les tâches TODO stockées dans le fichier spécifié.
     *
     * @param filePath Le chemin vers le fichier à partir duquel lister les tâches.
     * @return Une liste de toutes les tâches trouvées.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    List<Todo> listTodos(Path filePath) throws IOException;

    /**
     * (Optionnel) Écrit une liste de tâches TODO dans le fichier spécifié,
     * remplaçant tout contenu existant.
     *
     * @param filePath Le chemin vers le fichier où écrire les tâches.
     * @param todos La liste des tâches à écrire.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    default void writeTodos(Path filePath, List<Todo> todos) throws IOException {
        throw new UnsupportedOperationException("This method is not implemented.");
    }
}
