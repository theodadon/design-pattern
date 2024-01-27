package com.fges.todoapp;

import org.apache.commons.cli.*;

import java.util.List;

public class CommandInterface {

    public static int execute(String[] args) {
        Options options = new Options();
        options.addRequiredOption("s", "source", true, "File containing the todos");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            ErreurManager.handleError("Echec d'analyse des arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        List<String> positionalArgs = cmd.getArgList();

        if (((List<?>) positionalArgs).isEmpty()) {
            ErreurManager.handleError("Manque commande");
            return 1;
        }

        String command = positionalArgs.get(0);
        TodoListManager manager;

        if (fileName.endsWith(EnvManager.JSON_EXTENSION)) {
            manager = new JsonManager(fileName);
        } else if (fileName.endsWith(EnvManager.CSV_EXTENSION)) {
            manager = new CsvManager(fileName);
        } else {
            ErreurManager.handleError("Unsupported file format.");
            return 1;
        }

        try {
            switch (command) {
                case "insert":
                    if (positionalArgs.size() < 2) {
                        ErreurManager.handleError("Missing TODO name");
                        return 1;
                    }
                    manager.insert(positionalArgs.get(1));
                    break;
                case "list":
                    manager.list();
                    break;
                default:
                    ErreurManager.handleError("commande inconnue");
                    return 1;
            }
        } catch (Exception e) {
            ErreurManager.handleError("Erreur exec: " + e.getMessage());
            return 1;
        }

        return 0;
    }
}
