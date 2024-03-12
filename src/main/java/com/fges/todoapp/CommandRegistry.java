package com.fges.todoapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandRegistry {
    private final Map<String, Function<String[], Command>> commandConstructors = new HashMap<>();

    // Enregistre un constructeur de commande dans le registre
    public void registerCommand(String commandName, Function<String[], Command> constructor) {
        commandConstructors.put(commandName, constructor);
    }

    // Récupère une instance de la commande basée sur le nom de la commande
    public Command getCommand(String commandName, String[] args) {
        if (!commandConstructors.containsKey(commandName)) {
            LogManager.log("Unknown command (CommandRegistry): " + commandName + " " + Arrays.toString(args));
            throw new IllegalArgumentException("Unknown command: " + commandName + " " + Arrays.toString(args));
        }
        return commandConstructors.get(commandName).apply(args);
    }

    // Vérifie si une commande est enregistrée dans le registre
    public boolean isCommand(String commandName) {
        return commandConstructors.containsKey(commandName);
    }
}
