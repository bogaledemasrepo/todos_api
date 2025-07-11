package todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TodoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @ExceptionHandler(value = TodoNotFoundException.class)
    public ResponseEntity<Object> exception(TodoNotFoundException exception) {
        return null;
    }
}
