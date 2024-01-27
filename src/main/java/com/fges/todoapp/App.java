package com.fges.todoapp;

public class App {
    public static void main(String[] args) {
        CommandInterface cli = new CommandInterface();
        System.exit(cli.execute(args));
    }
}
