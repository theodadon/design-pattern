package com.fges.todoapp;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
/**
 * ListCommand permet de lister les tâches TODO stockées.
 * Elle implémente l'interface Command et exécute l'opération de listage en fonction des critères spécifiés.
 */
public class ListCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();
    private boolean showDoneOnly = false;

    public ListCommand(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--source":
                    if (i + 1 < args.length) {
                        argsMap.put("source", args[++i]);
                    } else {
                        throw new IllegalArgumentException("Expected a value after " + args[i]);
                    }
                    break;
                case "--done":
                case "-d":
                    showDoneOnly = true;
                    break;
            }
        }
    }

    @Override
    public int execute() {
        if (!argsMap.containsKey("source")) {
            LogManager.log("Source file path (-s or --source) is required for list command.");
            System.err.println("Source file path (-s or --source) is required for list command.");
            return 1;
        }
        String filePath = argsMap.get("source");
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));

        try {
            manager.listTodos(Paths.get(filePath)).forEach(todo -> {
                if (!showDoneOnly || todo.isDone()) {
                    String statusPrefix = todo.isDone() ? "- Done: " : "- ";
                    System.out.println(statusPrefix + todo.getDescription());
                }
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
