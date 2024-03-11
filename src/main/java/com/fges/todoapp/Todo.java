package com.fges.todoapp;

public class Todo {
    private String description;
    private String author;
    private String color;
    private boolean done;
    public Todo(String description, String author, String color, boolean done) {
        this.description = description;
        this.author = author;
        this.color = color;
        this.done = done; // Initialise le statut de la tâche
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
