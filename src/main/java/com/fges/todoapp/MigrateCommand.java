package com.fges.todoapp;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * MigrateCommand permet de migrer les tâches TODO d'un format de fichier à un autre.
 * Elle implémente l'interface Command et gère le processus de migration.
 */
public class MigrateCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();

    public MigrateCommand(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--source":
                    argsMap.put("source", args[++i]);
                    break;
                case "-o":
                case "--output":
                    argsMap.put("output", args[++i]);
                    break;
            }
        }
    }

    @Override
    public int execute() {
        if (!argsMap.containsKey("source") || !argsMap.containsKey("output")) {
            LogManager.log("Both source and output paths are required for migrate command.");
            System.err.println("Both source and output paths are required for migrate command.");
            return 1;
        }

        String sourcePath = argsMap.get("source");
        String outputPath = argsMap.get("output");

        try {
            FileFormatManager sourceManager = FileFormatManagerFactory.getManager(getFileExtension(sourcePath));
            FileFormatManager outputManager = FileFormatManagerFactory.getManager(getFileExtension(outputPath));

            List<Todo> sourceTodos = sourceManager.listTodos(Paths.get(sourcePath));
            sourceTodos.forEach(todo -> LogManager.log("Before Migration - Source: " + sourcePath + ", Todo: " + todo.getDescription() + " (done: " + todo.isDone() + ")"));

            Path filePath = Paths.get(outputPath);
            List<Todo> outputTodos = outputManager.listTodos(filePath);
            outputTodos.addAll(sourceTodos);
            outputManager.writeTodos(filePath, outputTodos);
            outputTodos.forEach(todo -> LogManager.log("After Migration - Output: " + outputPath + ", Todo: " + todo.getDescription() + " (done: " + todo.isDone() + ")"));
            LogManager.log("Successfully migrated TODOs from " + sourcePath + " to " + outputPath);
            return 0;
        } catch (Exception e) {
            LogManager.log("Migration failed: " + e.getMessage());
            System.err.println("Migration failed: " + e.getMessage());
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
