package todo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo.exception.TodoNotFoundException;
import todo.model.Todo;

@RestController
@RequestMapping("todos")
public class TodoServiceController {
    ArrayList<String> name = new ArrayList<String>();

    public static Map<String, Todo> todos = new HashMap<>();

    static {
        Todo todo1 = new Todo();
        todo1.setId((Math.round(Math.random() * 1000000)));
        todo1.setTitle("Todo 1");
        todo1.setDiscription("Todo 1 discription!");
        todos.put("" + (todo1.getId()) + "", todo1);

        Todo todo2 = new Todo();
        todo2.setId(Math.round(Math.random() * 1000000));
        todo2.setTitle("Todo 2");
        todo2.setDiscription("Todo 2 discription!");
        todos.put("" + todo2.getId() + "", todo2);

        Todo todo3 = new Todo();
        todo3.setId(Math.round(Math.random() * 1000000));
        todo3.setTitle("Todo 3");
        todo3.setDiscription("Todo 3 discription!");
        todos.put("" + todo3.getId() + "", todo3);
    }

    // Getting all todo
    @GetMapping
    public ResponseEntity<Collection<Todo>> getTodos() {
        var result = todos.values();
        // Check if todos exist
        if (result.isEmpty()) {
            throw new TodoNotFoundException();
        }
        return ResponseEntity.ok(result);
    }

    // Getting single todo detail
    @GetMapping("/{todoId}")
    public ResponseEntity<Optional<Todo>> getTodo(@PathVariable long todoId) {
        // Check if todo exists by id
        var todo = todos.values().stream().filter(td -> td.getId() == todoId).findFirst();
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();

        }
        return ResponseEntity.ok(todo);
    }

    // Adding todo to todo list
    @PostMapping
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
        todo.setId(Math.round(Math.random() * 1000000));
        todos.put(todo.getTitle(), todo);
        return new ResponseEntity<>("Todo is created successfully",
                HttpStatus.CREATED);
    }

    // Update a todo
    @PutMapping("/{todoId}")
    public ResponseEntity<String> updateTodo(@PathVariable long todoId, @RequestBody Todo data) {
        // Check if todo exists by id
        var todo = todos.values().stream().filter(td -> td.getId() == todoId).findFirst();
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();

        }
        Todo updatedTodo = new Todo();
        updatedTodo.setId(todoId);
        updatedTodo.setTitle(data.getTitle());
        updatedTodo.setDiscription(data.getDiscription());
        todos.replace("" + todoId + "", updatedTodo);
        return ResponseEntity.ok("Todo successfully updated!");
    }
    // Delete todo

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable long todoId) {
        // Check if todo exists by id
        var todo = todos.values().stream().filter(td -> td.getId() == todoId).findFirst();
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();

        }
        todos.remove("" + todoId + "");
        return ResponseEntity.noContent().build();
    }

}
