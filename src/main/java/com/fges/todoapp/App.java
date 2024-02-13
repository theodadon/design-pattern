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
            int exitCode = TodoListManager.handleCommand(args);
            System.exit(exitCode);
        } catch (Exception e) {
            LogManager.log("An error occurred: " + e.getMessage());
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }

    public static int exec(String[] args) throws Exception {
        initializeApplication();
        if (args.length < 3 || !"-s".equals(args[0])) {
            throw new IllegalArgumentException("Usage: -s <filepath> <command> [args...]");
        }
        String filePath = args[1];
        String commandName = args[2];
        String[] commandArgs = new String[args.length - 1];
        commandArgs[0] = filePath;
        System.arraycopy(args, 3, commandArgs, 1, args.length - 3);

        return commandRegistry.getCommand(commandName, commandArgs).execute();
    }

    private static void initializeApplication() {
        FileFormatManagerFactory.registerFormatManager("csv", CsvManager.class);
        FileFormatManagerFactory.registerFormatManager("json", JsonManager.class);
        commandRegistry.registerCommand("insert", InsertCommand::new);
        commandRegistry.registerCommand("list", ListCommand::new);
    }
}
