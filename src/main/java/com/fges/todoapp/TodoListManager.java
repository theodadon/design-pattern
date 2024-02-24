package com.fges.todoapp;

/**
 * La classe TodoListManager est conçue pour gérer l'exécution des différentes
 * commandes de l'application todo.
 * Elle utilise un CommandRegistry pour enregistrer et récupérer les commandes
 * disponibles dans l'application.
 * Cette classe agit comme le point central pour le traitement des commandes dans
 * l'application de gestion des tâches. Elle délègue
 * la responsabilité de l'exécution spécifique de chaque commande aux classes de
 * commandes individuelles tout en fournissant une interface
 * unifiée pour leur exécution.
 */
public class TodoListManager {
    private static final CommandRegistry commandRegistry = new CommandRegistry();

    static {
        commandRegistry.registerCommand("insert", InsertCommand::new);
        commandRegistry.registerCommand("list", ListCommand::new);

    }

    public static int handleCommand(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Please specify a command.");
            return 1;
        }

        String commandType = args[0];
        try {
            return commandRegistry.getCommand(commandType, args).execute();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return 1;
        }
    }
}
