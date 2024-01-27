package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonManager implements TodoListManager {
    private final Path filePath;

    public JsonManager(String fileName) {
        this.filePath = FileManager.getFilePath(fileName);
    }

    @Override
    public void insert(String todo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj;

        if (Files.exists(filePath)) {
            actualObj = mapper.readTree(filePath.toFile());
        } else {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (!(actualObj instanceof ArrayNode)) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        ((ArrayNode) actualObj).add(todo);
        Files.writeString(filePath, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(actualObj));
        System.out.println("Todo ajouté avec succès en format JSON.");
    }

    @Override
    public void list() throws IOException {
        if (!Files.exists(filePath)) {
            System.out.println("Aucun todo trouvé.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(filePath.toFile());

        if (actualObj instanceof ArrayNode) {
            actualObj.forEach(node -> System.out.println("- " + node.asText()));
        }
    }
}
