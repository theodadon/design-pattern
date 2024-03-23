
# Project Contribution Guide

Welcome to our Todo Application project! This guide is aimed at newcomers who wish to contribute to the project. Below, you'll find detailed instructions on how to add new functionalities and features to our application.

## How Can a Newcomer Add a New Command

1. **Create a New Command Class**: Implement a new class in the `com.fges.todoapp` package that implements the `Command` interface. Your class should override the `execute()` method, where you'll define the logic of your new command.

    ```java
    package com.fges.todoapp;

    public class NewCommand implements Command {
        @Override
        public int execute() throws Exception {
            // Implement your command logic here
            return 0;
        }
    }
    ```

2. **Register Your Command**: In the `CommandRegistry` class, register your new command with a unique command name. This is done by adding a new entry to the `commandConstructors` map within the class's constructor or an initialization block.

    ```java
    commandRegistry.registerCommand("newcommand", NewCommand::new);
    ```

## How Can a Newcomer Add a New File-Based Datasource

1. **Implement the FileFormatManager Interface**: Create a new class that implements the `FileFormatManager` interface, providing implementations for methods to interact with your new file format.

2. **Register Your File Format**: Use the `FileFormatManagerFactory` to register your new file format manager. This is typically done by calling the `registerFormatManager` method and passing the file extension and your manager class.

    ```java
    FileFormatManagerFactory.registerFormatManager("myformat", MyFormatManager.class);
    ```

## How Can a Newcomer Add a Non-File-Based Datasource

1. **Define a New Datasource Manager**: Similar to adding a file-based datasource, define a class that encapsulates operations for your datasource. However, this class doesn't need to implement `FileFormatManager` and can follow its own design.

2. **Integrate Your Datasource**: Depending on how your datasource is meant to be used, integrate it within the application logic where needed. This might involve modifying existing classes to utilize your new datasource.

## How Can a Newcomer Add a New Attribute to a Todo

1. **Modify the Todo Class**: Add your new attribute as a field within the `Todo` class. Don't forget to update the constructor(s) and add getter/setter methods as necessary.

    ```java
    private String newAttribute;
    ```

2. **Update Related Functionalities**: Ensure that any class that creates, reads, or modifies `Todo` objects is updated to handle the new attribute. This includes updating file format managers if your attribute needs to be persisted.

## How Can a Newcomer Add a New Interface to the Project

1. **Define Your Interface**: Create a new Java interface in the `com.fges.todoapp` package. Define any methods that classes implementing this interface are expected to implement.

2. **Implement the Interface**: Create new classes or modify existing ones to implement your new interface. Ensure all required methods are properly implemented.


## Diagram class

1. You can found the [Diagram class](final.svg) here.