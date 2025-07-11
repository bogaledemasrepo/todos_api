package todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoExceptionControler {

    @ExceptionHandler(value = TodoNotFoundException.class)
    public ResponseEntity<Object> exception(TodoNotFoundException exception) {
        return new ResponseEntity<>("Todo not found",
                HttpStatus.NOT_FOUND);
    }
}
