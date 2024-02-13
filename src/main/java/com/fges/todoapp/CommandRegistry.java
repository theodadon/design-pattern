package com.fges.todoapp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/*Gère le registre des commandes disponibles dans l'application.
Cette classe permet l'enregistrement des commandes et la récupération
des instances de commandes basées sur les noms de commandes fournies par
l'utilisateur, facilitant ainsi l'exécution dynamique des commandes.*/
public class CommandRegistry {
    private final Map<String, Function<String[], Command>> commandConstructors = new HashMap<>();

    public void registerCommand(String commandName, Function<String[], Command> constructor) {
        commandConstructors.put(commandName, constructor);
    }

    public Command getCommand(String commandName, String[] args) {
        if (!commandConstructors.containsKey(commandName)) {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        return commandConstructors.get(commandName).apply(args);
    }
}
