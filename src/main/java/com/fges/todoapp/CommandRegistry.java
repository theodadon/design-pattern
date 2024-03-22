package com.fges.todoapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/**
 * La classe CommandRegistry est responsable de l'enregistrement et de la récupération des commandes disponibles dans l'application.
 * Elle associe les noms de commande à leurs constructeurs respectifs.
 */
public class CommandRegistry {
    private final Map<String, Function<String[], Command>> commandConstructors = new HashMap<>();

    public void registerCommand(String commandName, Function<String[], Command> constructor) {
        commandConstructors.put(commandName, constructor);
    }

    public Command getCommand(String commandName, String[] args) {
        if (!commandConstructors.containsKey(commandName)) {
            LogManager.log("Unknown command (CommandRegistry): " + commandName + " " + Arrays.toString(args));
            throw new IllegalArgumentException("Unknown command: " + commandName + " " + Arrays.toString(args));
        }
        return commandConstructors.get(commandName).apply(args);
    }

    public boolean isCommand(String commandName) {
        return commandConstructors.containsKey(commandName);
    }
}
