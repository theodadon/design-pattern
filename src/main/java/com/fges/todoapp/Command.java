package com.fges.todoapp;
/**
 * L'interface Command définit le contrat pour les commandes dans l'application.
 * Chaque commande doit implémenter la méthode execute pour effectuer ses actions spécifiques.
 */
public interface Command {
    int execute() throws Exception;
}
