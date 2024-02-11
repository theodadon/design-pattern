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
        if (args.length < 3 || !"-s".equals(args[0])) {
            throw new IllegalArgumentException("Usage: -s <filepath> <command> [args...]");
        }
        String filePath = args[1];
        String commandName = args[2];
        String[] commandArgs = new String[args.length - 2];
        System.arraycopy(args, 2, commandArgs, 0, args.length - 2);

        commandArgs[0] = filePath;

        return commandRegistry.getCommand(commandName, commandArgs).execute();
    }

    private static void initializeApplication() {
        FileFormatManagerFactory.registerFormatManager("csv", CsvManager.class);
        FileFormatManagerFactory.registerFormatManager("json", JsonManager.class);

        commandRegistry.registerCommand("insert", InsertCommand::new);
        commandRegistry.registerCommand("list", ListCommand::new);
    }
}
