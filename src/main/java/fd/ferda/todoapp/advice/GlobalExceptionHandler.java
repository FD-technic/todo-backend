package fd.ferda.todoapp.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validException(MethodArgumentNotValidException ex) {

        List<FieldError> errorResponse = ex.getBindingResult().getFieldErrors();

        List<Map<String, String>> details =
                errorResponse.stream()
                        .map(err -> Map.of(
                                "field", err.getField(),
                                "message", err.getDefaultMessage()
                        ))
                        .toList();

        return ResponseEntity.badRequest().body(
                Map.of(
                        "error", "Validation failed",
                        "details", details
                )
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity pageNotFound(NoResourceFoundException ex) {

        return ResponseEntity.status(ex.getStatusCode()).body(
                Map.of(
                "error", ex.getStatusCode(),
                "massage", "Taková stránka tu není :-)")
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity badResponseStatus(ResponseStatusException ex) {

        return ResponseEntity.status(ex.getStatusCode()).body(
                Map.of(
                        "error", ex.getStatusCode(),
                        "massage", ex.getReason()
                )
        );
    }
}
