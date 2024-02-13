package com.fges.todoapp;

/*Représente l'entité Todo avec des propriétés comme la description,
l'auteur, et la couleur. Cette classe est utilisée pour modéliser les
données de todo qui sont traitées par l'application.*/
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
