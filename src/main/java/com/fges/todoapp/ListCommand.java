package com.fges.todoapp;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ListCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();

    public ListCommand(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--source":
                    if (i + 1 < args.length) { // Assurez-vous qu'il y a un argument après -s ou --source
                        argsMap.put("source", args[++i]);
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
        if (!argsMap.containsKey("source")) {
            System.err.println("Source file path (-s or --source) is required for list command.");
            return 1;
        }
        String filePath = argsMap.get("source");
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));

        manager.listTodos(Paths.get(filePath)).forEach(todo -> {
            String statusPrefix = todo.isDone() ? "Done: " : "- ";
            System.out.println(statusPrefix + todo.getDescription());
        });

        return 0;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
