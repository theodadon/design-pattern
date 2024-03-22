package com.fges.todoapp;

/**
 * La classe App sert de point d'entrée pour l'application de gestion de todo list.
 * Elle est responsable de l'initialisation de l'application et du traitement des arguments de ligne de commande.
 */
public class App {
    private static final CommandRegistry commandRegistry = new CommandRegistry();

    public static void main(String[] args) {
        int exitCode = exec(args);
        System.exit(exitCode);
    }

    public static int exec(String[] args) {
        try {
            if (args.length < 1) {
                throw new IllegalArgumentException("Usage: <command> [args...]");
            }
            return TodoListManager.handleCommand(args);
        } catch (Exception e) {
            LogManager.log("An error occurred (App): " + e.getMessage());
            System.err.println("An error occurred: " + e.getMessage());
            return 1;
        }
    }
}
