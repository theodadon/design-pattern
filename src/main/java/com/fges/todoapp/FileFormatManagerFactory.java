package com.fges.todoapp;

import java.util.HashMap;
import java.util.Map;

/*Une usine pour créer des instances de FileFormatManager basées sur
l'extension du fichier. Cette classe permet de récupérer le gestionnaire
de format de fichier approprié (par exemple, CsvManager ou JsonManager) en
fonction du type de fichier spécifié.*/
public class FileFormatManagerFactory {
    private static final Map<String, Class<? extends FileFormatManager>> registry = new HashMap<>();

    public static void registerFormatManager(String extension, Class<? extends FileFormatManager> managerClass) {
        registry.put(extension, managerClass);
    }

    public static FileFormatManager getManager(String extension) throws ReflectiveOperationException {
        Class<? extends FileFormatManager> managerClass = registry.get(extension);
        if (managerClass != null) {
            return managerClass.getDeclaredConstructor().newInstance();
        }
        throw new IllegalArgumentException("Unsupported file format: " + extension);
    }
}
