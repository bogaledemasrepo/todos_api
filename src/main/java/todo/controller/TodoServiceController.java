package todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import todo.exception.TodoNotFoundException;
import todo.model.Todo;
import todo.repository.TodosRepository;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("todos")
public class TodoServiceController {
    private final TodosRepository todosRepository;

    // Getting all todo
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        var todos = todosRepository.findAll();
        // Check if todos exist
        if (todos.isEmpty()) {
            throw new TodoNotFoundException();
        }
        return ResponseEntity.ok(todos);
    }

    // Getting single todo detail
    @GetMapping("/{todoId}")
    public ResponseEntity<Optional<Todo>> getTodo(@PathVariable long todoId) {
        // Check if todo exists by id
        var todo = todosRepository.findById(todoId);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();

        }
        return ResponseEntity.ok(todo);
    }

    // Adding todo to todo list
    @PostMapping
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
        todosRepository.save(todo);
        return new ResponseEntity<>("Todo is created successfully",
                HttpStatus.CREATED);
    }

    // Update a todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Optional<Todo>> updateTodo(@PathVariable long todoId, @RequestBody Todo data) {
        // Check if todo exists by id
        Optional<Todo> todo = todosRepository.findById(todoId);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();

        }
        var updateTodo = new Todo();
        updateTodo.setId(todoId);
        if (data.getTitle() != null) {
            updateTodo.setTitle(data.getTitle());
        } else
            updateTodo.setTitle(todo.get().getTitle());
        if (data.getDiscription() != null) {
            updateTodo.setDiscription(data.getDiscription());
        } else
            updateTodo.setTitle(todo.get().getDiscription());

        todosRepository.save(updateTodo);

        return ResponseEntity.ok(todo);
    }

    // Delete todo

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable long todoId) {
        // Check if todo exists by id
        var todo = todosRepository.findById(todoId);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();

        }
        todosRepository.deleteById(todoId);
        return ResponseEntity.noContent().build();
    }

}
