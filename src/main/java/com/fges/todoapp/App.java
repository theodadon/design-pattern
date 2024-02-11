package com.fges.todoapp;

public class App {
    private static final CommandRegistry commandRegistry = new CommandRegistry();

    public static void main(String[] args) {
        try {
            int exitCode = exec(args);
            System.exit(exitCode);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }

    public static int exec(String[] args) throws Exception {
        initializeApplication();
        if (args.length == 0) {
            throw new IllegalArgumentException("No command provided");
        }
        return commandRegistry.getCommand(args[0], args).execute();
    }

    private static void initializeApplication() {
        // Enregistrement des gestionnaires de format
        FileFormatManagerFactory.registerFormatManager("csv", CsvManager.class);
        FileFormatManagerFactory.registerFormatManager("json", JsonManager.class);

        // Enregistrement des commandes
        commandRegistry.registerCommand("insert", InsertCommand::new);
        commandRegistry.registerCommand("list", ListCommand::new);
    }
}
