package com.fges.todoapp;

public class Todo {
    private String description;
    private String author;
    private String color;
    public Todo(String description, String author, String color) {
        this.description = description;
        this.author = author;
        this.color = color;
    }

    // Getters et setters pour les nouveaux champs
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
}
