package com.fges.todoapp;
/*Le point d'entrée de l'application.
Cette classe est responsable de l'initialisation de l'application,
y compris la configuration des gestionnaires de format de fichiers
et l'enregistrement des commandes disponibles. Elle analyse également les arguments
de la ligne de commande et délègue l'exécution des commandes au CommandRegistry. */
public class App {
    private static final CommandRegistry commandRegistry = new CommandRegistry();

    public static void main(String[] args) {
        try {
            initializeApplication();
            int exitCode = exec(args);
            System.exit(exitCode);
        } catch (Exception e) {
            LogManager.log("An error occurred: " + e.getMessage());
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void initializeApplication() {
        FileFormatManagerFactory.registerFormatManager("csv", CsvManager.class);
        FileFormatManagerFactory.registerFormatManager("json", JsonManager.class);
        commandRegistry.registerCommand("insert", InsertCommand::new);
        commandRegistry.registerCommand("list", ListCommand::new);
    }

    public static int exec(String[] args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: <command> [args...]");
        }
        return TodoListManager.handleCommand(args); // Utilisation de TodoListManager
    }
}
