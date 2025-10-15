package pe.idat.template.dto.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroCreateDTO {
    @NotBlank
    @Size(max = 200)
    private String titulo;

    @NotBlank @Size(max = 100)
    private String autor;

    @NotBlank
    @Size(min = 13, max = 13, message = "El ISBN debe tener exactamente 13 caracteres")
    private String isbn;

    @NotNull
    @Min(1500) @Max(2025)
    private Integer anioPublicacion;

    @NotNull @Min(0)
    private Integer stock;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double precio;

}
