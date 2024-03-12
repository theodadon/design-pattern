package com.fges.todoapp;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ListCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();

    public ListCommand(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("-s".equals(args[i]) || "--source".equals(args[i])) {
                argsMap.put("source", args[++i]);
            }
        }
    }

    @Override
    public int execute() throws ReflectiveOperationException {
        if (!argsMap.containsKey("source")) {
            LogManager.log("Source file path (-s or --source) is required for list command.");
            System.err.println("Source file path (-s or --source) is required for list command.");
            return 1;
        }
        String filePath = argsMap.get("source");
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));

        try {
            manager.listTodos(Paths.get(filePath)).forEach(todo -> {
                String statusPrefix = todo.isDone() ? " - Done: " : "- Don DADA ";
                System.out.println(statusPrefix + todo.getDescription());
            });
            return 0;
        } catch (Exception e) {
            LogManager.log("An error occurred: " + e.getMessage());
            System.err.println("An error occurred: " + e.getMessage());
            return 1;
        }
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
