package com.fges.todoapp;

import org.apache.commons.cli.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TodoListManager {

    private static final Map<String, FileFormatManager> formatManagers = new HashMap<>();

    static {
        // Register file format managers
        formatManagers.put("csv", new CsvManager());
        formatManagers.put("json", new JsonManager());
    }

    public static int handleCommand(String[] args) throws IOException {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            ErreurManager.printError("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        String command = cmd.getArgList().isEmpty() ? "" : cmd.getArgList().get(0);
        String fileExtension = getFileExtension(fileName);

        FileFormatManager manager = formatManagers.get(fileExtension);
        if (manager == null) {
            ErreurManager.printError("Unsupported file format: " + fileExtension);
            return 1;
        }

        switch (command) {
            case "insert":
                return handleInsertCommand(cmd, fileName, manager);
            case "list":
                return handleListCommand(fileName, manager);
            default:
                ErreurManager.printError("Missing or unknown command");
                return 1;
        }
    }

    private static int handleInsertCommand(CommandLine cmd, String fileName, FileFormatManager manager) throws IOException {
        if (cmd.getArgList().size() < 2) {
            ErreurManager.printError("Missing TODO name");
            return 1;
        }

        String todo = cmd.getArgList().get(1);
        manager.insertTodo(Paths.get(fileName), todo);
        return 0;
    }

    private static int handleListCommand(String fileName, FileFormatManager manager) throws IOException {
        String fileContent = FileManager.readFileContent(Paths.get(fileName));
        manager.listTodos(fileContent);
        return 0;
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}
