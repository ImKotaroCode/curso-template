package pe.idat.template.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String codigo;
    private String mensaje;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String detalles;
}
