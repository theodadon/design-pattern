package com.fges.todoapp;

/*Le point d'entrée de l'application.
Cette classe est responsable de l'initialisation de l'application,
y compris la configuration des gestionnaires de format de fichiers
et l'enregistrement des commandes disponibles. Elle analyse également les arguments
de la ligne de commande et délègue l'exécution des commandes au CommandRegistry. */

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
