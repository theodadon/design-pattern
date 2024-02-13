package com.fges.todoapp;

import java.nio.file.Paths;

/*
Implémente l'interface Command. Cette commande est responsable de l'ajout
d'un nouveau todo dans le fichier spécifié, en utilisant les informations
fournies par l'utilisateur.*/
public class InsertCommand implements Command {
    private final String[] args;

    public InsertCommand(String[] args) {
        this.args = args;
    }

    @Override
    public int execute() throws Exception {
        if (args.length < 2) {
            System.err.println("Not enough arguments for insert command.");
            return 1;
        }
        String filePath = args[0]; // args[0] est maintenant le chemin du fichier
        String description = args[1]; // La description est le premier argument spécifique de la commande
        String author = args.length > 2 ? args[2] : "Anonymous"; // L'auteur est optionnel
        String color = args.length > 3 ? args[3] : "None"; // La couleur est optionnelle

        Todo todo = new Todo(description, author, color);
        FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(filePath));
        manager.insertTodo(Paths.get(filePath), todo);
        System.out.println("Todo added.");
        LogManager.log("New todo added: " + todo.getDescription() + " by " + todo.getAuthor() + " in " + filePath);
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
