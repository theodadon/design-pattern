package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.io.IOException;
import java.nio.file.Path;

public class JsonManager implements FileFormatManager {

    @Override
    public void insertTodo(Path filePath, String todo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String content = FileManager.readFileContent(filePath);
        JsonNode actualObj = mapper.readTree(content);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(todo);
        }

        FileManager.writeFileContent(filePath, actualObj.toString());
    }

    @Override
    public void listTodos(String fileContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> System.out.println("- " + node.asText()));
        }
    }
}
