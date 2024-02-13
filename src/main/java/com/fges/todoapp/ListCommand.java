package com.fges.todoapp;

import java.nio.file.Paths;

public class ListCommand implements Command {
    private final String[] args;

    public ListCommand(String[] args) {
        this.args = args;
    }

    @Override
    public int execute() throws Exception {
        if (args.length < 1) {
            System.err.println("Not enough arguments for list command.");
            return 1;
        }
        String filePath = args[0]; // args[0] est le chemin du fichier
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));
        manager.listTodos(Paths.get(filePath)).forEach(todo ->
                System.out.println(todo.getDescription() + " - Author: " + todo.getAuthor() + ", Color: " + todo.getColor())
        );
        return 0;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
