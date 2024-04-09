package com.project.mytodos.service;

import com.project.mytodos.Repositories.DeletedTodoRepo;
import com.project.mytodos.Repositories.ToDoRepo;
import com.project.mytodos.entity.AddResponse;
import com.project.mytodos.entity.Deletedtodo;
import com.project.mytodos.entity.Todo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ToDoServiceImplementation implements ToDoService {
    @Autowired
     ToDoRepo toDoRepo;
    @Autowired
    DeletedTodoRepo deletedTodoRepo;


    public List<Todo> getTodosByDone(boolean done) {
        return toDoRepo.findByDone(done);
    }
    @Override
    public List<Todo> getAllTodos() {

        return toDoRepo.findAll();
    }
    @Override
    public Todo getToDosById(int id) throws Exception {
        Optional<Todo> toDoResource = toDoRepo.findById(id);
        if(toDoResource.isPresent()){
            return toDoResource.get();
        }else
            throw new Exception( "TO Dos Not found");

    }





    @Override
    public Todo getToDosByName(String userName) throws Exception {
        List<Todo> todoList = toDoRepo.findAll();
        Todo todo=null;
        for(Todo tod: todoList){
            if(tod.getUserName().equalsIgnoreCase(userName))
                todoList.add(tod);
                    todo =tod ;
        }

        return (Todo) todo;
    }

    @Override
    public Todo addToDoService(Todo toDoResource) {
        toDoResource.setId(getMaxId());
        toDoRepo.save(toDoResource);
        return toDoResource;
    }
    public  int getMaxId(){
        return toDoRepo.findAll().size()+1;

    }
    @Override
    public AddResponse deleteToDos(int id) {
        toDoRepo.deleteById(id);
        AddResponse response =new AddResponse();
        response.setMsg("To Do Deleted");
        response.setId(id);
        return response;

    }

    public String deleteTodoById(int id) {
        // Retrieve the Todo object to be deleted
        Optional<Todo> optionalTodo = toDoRepo.findById(id);

        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();

            // Create a DeletedTodo object to store the deleted Todo information
            Deletedtodo deletedTodo = new Deletedtodo();
            deletedTodo.setId(todo.getId());
            deletedTodo.setUserName(todo.getUserName());
            deletedTodo.setDescription(todo.getDescription());
            deletedTodo.setTargetDate(todo.getTargetDate());
            deletedTodo.setDone(todo.isDone());

            // Save the deleted Todo information to the DeletedTodo table
            deletedTodoRepo.save(deletedTodo);

            // Delete the Todo from the Todo table
            toDoRepo.deleteById(id);

            return "Todo with ID " + id + " has been successfully deleted.";
        } else {
            return "Todo with ID " + id + " not found.";
        }
    }

    @Override
    public String deleteDeletedTodoById(int id) throws Exception {
        if (deletedTodoRepo.existsById(id)) {
            // If it exists, delete it
            deletedTodoRepo.deleteById(id);
        } else {
            // If the deleted todo does not exist, throw an exception
            throw new Exception("Deleted todo with ID " + id + " does not exist");
        }

        return "Todo with ID " + id + " not found.";
    }

    @Override
    public List<Deletedtodo> getAllDeletedTodos() {
        return deletedTodoRepo.findAll();
    }

    @Override
    public Todo updateToDos(Todo toDoResource) throws Exception {
        toDoRepo.save(toDoResource);
        return toDoResource;

    }





}
