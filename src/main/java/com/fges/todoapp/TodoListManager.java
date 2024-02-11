package com.fges.todoapp;

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
