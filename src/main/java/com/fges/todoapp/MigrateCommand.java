package com.fges.todoapp;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MigrateCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();

    public MigrateCommand(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--source":
                    if (i + 1 < args.length) {
                        argsMap.put("source", args[++i]);
                    }
                    break;
                case "-o":
                case "--output":
                    if (i + 1 < args.length) {
                        argsMap.put("output", args[++i]);
                    }
                    break;
                default:
                    // Autres arguments inattendus
                    break;
            }
        }
    }

    @Override
    public int execute() throws Exception {
        if (!argsMap.containsKey("source") || !argsMap.containsKey("output")) {
            System.err.println("Both source (-s or --source) and output (-o or --output) paths are required for migrate command.");
            return 1;
        }
        String sourcePath = argsMap.get("source");
        String outputPath = argsMap.get("output");

        FileFormatManager sourceManager = FileFormatManagerFactory.getManager(getFileExtension(sourcePath));
        FileFormatManager outputManager = FileFormatManagerFactory.getManager(getFileExtension(outputPath));

        List<Todo> sourceTodos = sourceManager.listTodos(Paths.get(sourcePath));
        Path filePath = Paths.get(outputPath);
        List<Todo> outputTodos = outputManager.listTodos(filePath);

        // Ajoutez tous les TODOs de source à la fin de la liste de destination
        outputTodos.addAll(sourceTodos);

        // Écrivez la liste mise à jour dans le fichier de sortie

        for (Todo todo : outputTodos) {
            outputManager.insertTodo(filePath, todo);
        }

        System.out.println("Migrated TODOs from " + sourcePath + " to " + outputPath);
        return 0;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
