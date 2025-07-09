package todo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo.model.Todo;

@RestController
@RequestMapping("todos")
public class TodoServiceController {
    ArrayList<String> name = new ArrayList<String>();

    public static Map<String, Todo> todos = new HashMap<>();

    static {
        Todo todo1 = new Todo();
        todo1.setId(1);
        todo1.setTitle("Todo 1");
        todo1.setDiscription("Todo 1 discription!");
        todos.put(todo1.getTitle(), todo1);

        Todo todo2 = new Todo();
        todo2.setId(2);
        todo2.setTitle("Todo 2");
        todo2.setDiscription("Todo 2 discription!");
        todos.put(todo2.getTitle(), todo2);

        Todo todo3 = new Todo();
        todo3.setId(3);
        todo3.setTitle("Todo 3");
        todo3.setDiscription("Todo 3 discription!");
        todos.put(todo3.getTitle(), todo3);
    }

    // Getting all todo
    @GetMapping
    public ResponseEntity<Collection<Todo>> getTodos() {
        return ResponseEntity.ok(todos.values());
    }

    // Getting single todo detail
    @GetMapping("/{todoId}")
    public ResponseEntity<Optional<Todo>> getTodo(@PathVariable long todoId) {
        return ResponseEntity.ok(todos.values().stream().filter(todo -> todo.getId() == todoId).findFirst());
    }
}
