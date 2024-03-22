package com.fges.todoapp;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * FileFormatManagerFactory est une fabrique pour créer des instances de FileFormatManager en fonction de l'extension de fichier.
 * Elle permet de séparer les opérations sur les fichiers du format de fichier utilisé.
 */
public class FileFormatManagerFactory {
    private static final Map<String, Class<? extends FileFormatManager>> registry = new HashMap<>();

    public static void registerFormatManager(String extension, Class<? extends FileFormatManager> managerClass) {
        registry.put(extension, managerClass);
    }

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
