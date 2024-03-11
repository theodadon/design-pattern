package com.fges.todoapp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;

/*
Implémente l'interface Command. Cette commande est responsable de l'ajout
d'un nouveau todo dans le fichier spécifié, en utilisant les informations
fournies par l'utilisateur.*/
public class InsertCommand implements Command {
    private final Map<String, String> argsMap = new HashMap<>();
    private String description;
    private boolean isDone;

    public InsertCommand(String[] args) {
        System.out.println(Arrays.toString(args));
        for (int i = 0; i < args.length; i++) {
            if ("-s".equals(args[i]) || "--source".equals(args[i])) {
                argsMap.put("source", args[++i]);
            } else if ("-d".equals(args[i]) || "--done".equals(args[i])) {
                isDone = true;
            } else {
                description = args[i];
            }
        }
    }

    @Override
    public int execute() throws Exception {
        if (!argsMap.containsKey("source")) {
            System.err.println("File path is required for insert command.");
            return 1;
        }
        String filePath = argsMap.get("source");
        Todo todo = new Todo(description, "Anonymous", "None", isDone);
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));
        manager.insertTodo(Paths.get(filePath), todo);
        System.out.println("Todo added.");
        LogManager.log("New todo added: " + todo.getDescription() + " in " + filePath + " with status " + (isDone ? "DONE" : "PENDING"));
        return 0;
    }

    private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            throw new IllegalArgumentException("File " + fileName + " does not have a valid extension.");
        }
    }
}
