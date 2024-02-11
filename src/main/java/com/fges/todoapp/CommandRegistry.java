package com.fges.todoapp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandRegistry {
    private final Map<String, Function<String[], Command>> commandConstructors = new HashMap<>();

    public void registerCommand(String commandName, Function<String[], Command> constructor) {
        commandConstructors.put(commandName, constructor);
    }

    public Command getCommand(String commandName, String[] args) throws Exception {
        if (!commandConstructors.containsKey(commandName)) {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        return commandConstructors.get(commandName).apply(args);
    }
}
