package com.fges.todoapp;

import java.util.Arrays;

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
        commandRegistry.registerCommand("migrate", MigrateCommand::new);
        // Ajoutez d'autres commandes ici au besoin.
    }

    public static int handleCommand(String[] args) throws Exception {
        // Réorganiser les arguments pour que le nom de la commande soit en premier
        String[] reorderedArgs = reorderArguments(args);
        if (reorderedArgs.length < 1) {
            System.err.println("Please specify a command.");
            return 1;
        }

        String commandType = reorderedArgs[0];
        String[] commandArgs = Arrays.copyOfRange(reorderedArgs, 1, reorderedArgs.length);

        try {
            return commandRegistry.getCommand(commandType, commandArgs).execute();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return 1;
        }
    }

    private static String[] reorderArguments(String[] args) {
        // Mettez ici la logique de réorganisation. Par exemple, cherchez le premier mot clé de commande valide.
        // Ceci est juste un exemple. Vous devrez adapter cette méthode selon vos besoins spécifiques.
        for (int i = 0; i < args.length; i++) {
            if ("insert".equals(args[i]) || "list".equals(args[i]) || "migrate".equals(args[i]) || "web".equals(args[i])) {
                String command = args[i];
                String[] newArgs = new String[args.length];
                newArgs[0] = command;
                int newIndex = 1;
                for (int j = 0; j < args.length; j++) {
                    if (j != i) {
                        newArgs[newIndex++] = args[j];
                    }
                }
                return newArgs;
            }
        }
        return args;
    }
}

