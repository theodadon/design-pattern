package com.fges.todoapp;

public class App {
    private static final CommandRegistry commandRegistry = new CommandRegistry();

    public static void main(String[] args) {
        LogManager.log("Starting TodoApp");
        // Initialize the application

        // Execute the command and capture the exit code
        int exitCode = exec(args);

        // Exit the application with the appropriate exit code
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
