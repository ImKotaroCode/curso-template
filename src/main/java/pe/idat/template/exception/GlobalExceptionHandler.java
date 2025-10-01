package pe.idat.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pe.idat.template.dto.response.ApiResponse;
import pe.idat.template.dto.response.ErrorResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleUsuarioNotFoundException(
            UsuarioNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Usuario no encontrado", errorResponse));
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleProductoNotFoundException(
            ProductoNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Producto no encontrado", errorResponse));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error("Email ya existe", errorResponse));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error de validación", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleGlobalException(
            Exception ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error interno del servidor", errorResponse));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Argumento inválido", errorResponse));
    }
}
