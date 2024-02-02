package com.fges.todoapp;

import java.io.IOException;


public class App {

    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        return TodoListManager.handleCommand(args);
    }
}
