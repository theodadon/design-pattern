package com.fges.todoapp;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
/**
 * InsertCommand permet d'ajouter une nouvelle tâche TODO dans la liste.
 * Elle implémente l'interface Command et exécute l'opération d'insertion.
 */
public class InsertCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();
    private boolean isDone = false;
    private final String description;

    public InsertCommand(String[] args) {
        StringBuilder descriptionBuilder = new StringBuilder();
        boolean isDescriptionStarted = false;

        for (int i = 0; i < args.length; i++) {
            // Détecte et extrait les arguments attendus
            if (args[i].equals("-s") || args[i].equals("--source")) {
                if (i + 1 < args.length) {
                    argsMap.put("source", args[++i]);
                } else {
                    throw new IllegalArgumentException("Expected a value after " + args[i]);
                }
            } else if (args[i].equals("-d") || args[i].equals("--done") || args[i].equals("-done")) {
                isDone = true;
            } else {
                if (isDescriptionStarted) {
                    descriptionBuilder.append(" ");
                }
                descriptionBuilder.append(args[i]);
                isDescriptionStarted = true;
            }
        }

        if (!descriptionBuilder.isEmpty()) {
            description = descriptionBuilder.toString();
        } else {
            throw new IllegalArgumentException("No description provided for the task.");
        }
    }

    @Override
    public int execute() {
        if (!argsMap.containsKey("source")) {
            System.err.println("File path is required for insert command.");
            return 1;
        }
        String filePath = argsMap.get("source");
        try {
            Todo todo = new Todo(description, "Anonymous", "None", isDone);
            FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));
            manager.insertTodo(Paths.get(filePath), todo);
            return 0;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            return 1;
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1 || lastIndexOfDot == 0) {
            throw new IllegalArgumentException("File " + fileName + " does not have a valid extension.");
        }
        return fileName.substring(lastIndexOfDot + 1);
    }
}
