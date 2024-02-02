package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;


public class CsvManager implements FileFormatManager {

    @Override
    public void insertTodo(Path filePath, String todo) throws IOException {
        String content = FileManager.readFileContent(filePath);
        if (!content.endsWith("\n") && !content.isEmpty()) {
            content += "\n";
        }
        content += todo;
        FileManager.writeFileContent(filePath, content);
    }

    @Override
    public void listTodos(String fileContent) {
        System.out.println(fileContent.lines()
                .map(todo -> "- " + todo)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No TODOs found."));
    }
}
