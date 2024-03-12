package com.fges.todoapp;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FileFormatManagerFactory {
    private static final Map<String, Class<? extends FileFormatManager>> registry = new HashMap<>();

    // Enregistre une classe de gestionnaire pour une extension de fichier donnée
    public static void registerFormatManager(String extension, Class<? extends FileFormatManager> managerClass) {
        registry.put(extension, managerClass);
    }

    // Récupère une instance de FileFormatManager basée sur l'extension de fichier
    public static FileFormatManager getManager(String extension) {
        Class<? extends FileFormatManager> managerClass = registry.get(extension);
        if (managerClass == null) {
            throw new IllegalArgumentException("Unsupported file format: " + extension);
        }
        try {
            return managerClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogManager.log("Error creating instance of FileFormatManager: " + e.getMessage());
            throw new RuntimeException("Error creating instance of FileFormatManager: " + e.getMessage(), e);
        }
    }
}
