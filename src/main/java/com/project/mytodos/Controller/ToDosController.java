package com.project.mytodos.Controller;

import com.project.mytodos.Repositories.DeletedTodoRepo;
import com.project.mytodos.Repositories.ToDoRepo;
import com.project.mytodos.entity.AddResponse;
import com.project.mytodos.entity.Deletedtodo;
import com.project.mytodos.entity.Todo;
import com.project.mytodos.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ToDosController {
    @Autowired
    ToDoService toDoService;
    @Autowired
    ToDoRepo toDoRepo;
    @Autowired
    DeletedTodoRepo deletedTodoRepo;


    @GetMapping("/getToDos")
    public List<Todo> getTodos(){
        return toDoService.getAllTodos();

    }
    @GetMapping("/getToDos/{id}")
    public ResponseEntity<Todo> getToDosById(@PathVariable(value = "id") int id) throws Exception {
       try {
           Todo toDoResource= toDoService.getToDosById(id);
           return new ResponseEntity<Todo>(toDoResource,HttpStatus.OK);
       }
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/getToDosDone/{isDone}")

    public ResponseEntity<List<Todo>> getTodosByDone(@PathVariable("isDone") boolean isDone) {
        try {
            List<Todo> todos = toDoService.getTodosByDone(isDone);
            return new ResponseEntity<>(todos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getToDoNames/{username}")
    public ResponseEntity<Todo> getToDosByName(@PathVariable(value = "username") String userName) throws Exception {
        try {
            Todo toDoResource= toDoService.getToDosByName(userName);
            return new ResponseEntity<Todo>(toDoResource,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/getAllDeleted")
    public List<Deletedtodo> getAllDeletedTodos(){
        return toDoService.getAllDeletedTodos();

    }

    @DeleteMapping("/deleteDeletedTodo/{id}")
    public ResponseEntity<String> deleteDeletedTodoById(@PathVariable int id) {
        deletedTodoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addTodos")
    public Todo addToDOs(@RequestBody Todo toDoResource){

        return toDoService.addToDoService(toDoResource);
    }

    @PutMapping("/updateTodos/{id}")
    public ResponseEntity<Todo> updateToDOs(@PathVariable(value="id") int id, @RequestBody Todo toDoResource){
        try {
            Todo existToDos = toDoService.getToDosById(id);
            //existToDos.setUserName(toDoResource.getUserName());
            existToDos.setDescription(toDoResource.getDescription());
            existToDos.setDone(toDoResource.isDone());
            existToDos.setTargetDate(toDoResource.getTargetDate());
            Todo updated_toDos= toDoService.updateToDos(existToDos);
            return new ResponseEntity<Todo>(toDoResource,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }
    @DeleteMapping("/deletetodos/{id}")

    public ResponseEntity<String> deleteTodoById(@PathVariable(value="id") int id) {
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

           // return ResponseEntity.ok("Todo with ID " + id + " has been successfully deleted.");
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


