package com.fges.todoapp;

/*Définit le contrat que toutes les commandes doivent respecter.
Chaque commande doit implémenter une méthode execute qui contient la
logique d'exécution de la commande.*/
public interface Command {
    int execute() throws Exception;
}
