package com.fges.todoapp;

import java.util.Arrays;
/**
 * TodoListManager est responsable de la gestion des commandes relatives aux tâches TODO.
 * Elle initialise les commandes et gère leur exécution basée sur les arguments fournis.
 */
public class TodoListManager {
    private static final CommandRegistry commandRegistry = new CommandRegistry();
    private static boolean initialized = false;

    static {
        commandRegistry.registerCommand("insert", InsertCommand::new);
        commandRegistry.registerCommand("list", ListCommand::new);
        commandRegistry.registerCommand("migrate", MigrateCommand::new);
        commandRegistry.registerCommand("web", WebCommand::new);
    }

    public static void initialize() {
        if (!initialized) {
            FileFormatManagerFactory.registerFormatManager("csv", CsvManager.class);
            FileFormatManagerFactory.registerFormatManager("json", JsonManager.class);
            initialized = true;
        }
    }

    public static int handleCommand(String[] args) throws Exception {
        initialize();

        String[] reorderedArgs = reorderArguments(args);
        if (reorderedArgs.length < 1) {
            LogManager.log("Please specify a command. (TodoListManager:handleCommand)");
            System.err.println("Please specify a command.");
            return 1;
        }

        String commandType = reorderedArgs[0];
        String[] commandArgs = Arrays.copyOfRange(reorderedArgs, 1, reorderedArgs.length);

        try {
            return commandRegistry.getCommand(commandType, commandArgs).execute();
        } catch (IllegalArgumentException e) {
            LogManager.log("Invalid command (TodoListManager:handleCommand): " + commandType + ". " + e.getMessage());
            System.err.println(e.getMessage());
            return 1;
        }
    }

    private static String[] reorderArguments(String[] args) {
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
