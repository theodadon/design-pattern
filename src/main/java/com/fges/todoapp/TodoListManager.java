package com.fges.todoapp;

import java.io.IOException;

public interface TodoListManager {
    void insert(String todo) throws IOException;
    void list() throws IOException;
}
