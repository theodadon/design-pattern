package com.fges.todoapp;

import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
/**
 * WebCommand fournit une interface web pour interagir avec les tâches TODO.
 * Elle implémente l'interface Command et lance un serveur HTTP pour traiter les requêtes.
 */
public class WebCommand implements Command {
    private final String dataSource;

    public WebCommand(String[] args) {
        String localDataSource = "default.json";
        for (int i = 0; i < args.length; i++) {
            if ("-s".equals(args[i]) || "--source".equals(args[i])) {
                if (i + 1 < args.length) {
                    localDataSource = args[++i];
                } else {
                    LogManager.log("Expected a filename after " + args[i]);
                    throw new IllegalArgumentException("Expected a filename after " + args[i]);
                }
            }
        }
        this.dataSource = localDataSource;
    }

    @Override
    public int execute() {
        try {
            HttpServer server = HttpServer.create(new java.net.InetSocketAddress(8080), 0);
            FileFormatManager manager = FileFormatManagerFactory.getManager(getFileExtension(this.dataSource));

            server.createContext("/todos", exchange -> {
                String response;
                int statusCode = 200;

                try {
                    Path filePath = Paths.get(dataSource);
                    if ("GET".equals(exchange.getRequestMethod())) {
                        List<Todo> todos = manager.listTodos(filePath);
                        response = todos.toString();
                    } else if ("POST".equals(exchange.getRequestMethod())) {
                        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                        BufferedReader br = new BufferedReader(isr);
                        String description = br.readLine();
                        br.close();
                        isr.close();

                        Todo newTodo = new Todo(description, "Web User", "None", false);
                        manager.insertTodo(filePath, newTodo);
                        response = "Todo added successfully: " + description;
                    } else {
                        response = "Unsupported HTTP method: " + exchange.getRequestMethod();
                        statusCode = 405;
                    }
                } catch (Exception e) {
                    LogManager.log("Error processing request: " + e.getMessage());
                    response = "Error processing request: " + e.getMessage();
                    statusCode = 500;
                }

                exchange.sendResponseHeaders(statusCode, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            });

            LogManager.log("Server started on port 8080");
            server.start();
            return 0;
        } catch (IOException e) {
            LogManager.log("Server failed to start: " + e.getMessage());
            return 1;
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1 || lastIndexOfDot == 0) {
            LogManager.log("File " + fileName + " does not have a valid extension.");
            throw new IllegalArgumentException("File " + fileName + " does not have a valid extension.");
        }
        return fileName.substring(lastIndexOfDot + 1);
    }
}
