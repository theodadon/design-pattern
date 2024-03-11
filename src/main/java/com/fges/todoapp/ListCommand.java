package com.fges.todoapp;

import java.nio.file.Paths;

/**
 * Classe ListCommand implémentant l'interface Command. Cette classe est responsable
 * de la commande "list",
 * qui affiche les todos stockés dans un fichier donné. Elle prend en charge différents
 * formats de fichiers
 * en utilisant le FileFormatManager adéquat récupéré via FileFormatManagerFactory.
 * L'utilisation de cette commande permet aux utilisateurs de visualiser facilement
 * la liste des tâches à faire
 * stockées dans un fichier, peu importe le format du fichier, tant qu'un gestionnaire
 * approprié est disponible.
 */
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
        String filePath = args[0];
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
