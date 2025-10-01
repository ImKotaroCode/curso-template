package pe.idat.template.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private Boolean status;
    private String mensaje;
    private T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    // Métodos estáticos para crear respuestas de éxito
    public static <T> ApiResponse<T> success(String mensaje, T data) {
        return ApiResponse.<T>builder()
                .status(true)
                .mensaje(mensaje)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return success("Operación exitosa", data);
    }

    public static ApiResponse<Void> success(String mensaje) {
        return ApiResponse.<Void>builder()
                .status(true)
                .mensaje(mensaje)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Métodos estáticos para crear respuestas de error
    public static <T> ApiResponse<T> error(String mensaje, T data) {
        return ApiResponse.<T>builder()
                .status(false)
                .mensaje(mensaje)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Void> error(String mensaje) {
        return ApiResponse.<Void>builder()
                .status(false)
                .mensaje(mensaje)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
