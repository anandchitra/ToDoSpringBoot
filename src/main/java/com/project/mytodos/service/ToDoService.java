package com.project.mytodos.service;

import com.project.mytodos.entity.AddResponse;
import com.project.mytodos.entity.Deletedtodo;
import com.project.mytodos.entity.Todo;

import java.util.List;

public interface ToDoService {
    List<Todo> getAllTodos();
    Todo getToDosById(int id) throws Exception;
    public List<Todo> getTodosByDone(boolean done) throws Exception;
    Todo getToDosByName(String userName) throws Exception;
    Todo addToDoService(Todo toDoResource);
    AddResponse deleteToDos(int id) ;
    Todo updateToDos(Todo toDoResource)throws Exception;
    public String deleteTodoById(int id) throws Exception;
    public String deleteDeletedTodoById(int id) throws Exception;
    List<Deletedtodo> getAllDeletedTodos();





}
