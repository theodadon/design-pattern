package com.fges.todoapp;

import java.nio.file.Paths;

public class InsertCommand implements Command {
    private final String[] args;

    public InsertCommand(String[] args) {
        this.args = args;
    }

    @Override
    public int execute() throws Exception {
        if (args.length < 4) {
            System.err.println("Not enough arguments for insert command.");
            return 1;
        }
        String filePath = args[1];
        String description = args[2];
        String author = args[3];
        String color = args.length > 4 ? args[4] : "None";

        Todo todo = new Todo(description, author, color);
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));
        manager.insertTodo(Paths.get(filePath), todo);
        System.out.println("Todo added.");
        return 0;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
